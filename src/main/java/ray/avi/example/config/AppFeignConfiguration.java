package ray.avi.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.http.HttpHeaders;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

@Configuration
@Slf4j
public class AppFeignConfiguration {
	
	@Value("${exampleApp.service.restuser}") 
	private String restUser;

	@Autowired
	private SecurityConfig securityConfig;

	@Bean(name = "authorizationHeader")
	String getAuthorizationHeader() {
		UserAttributeEditor editor = new UserAttributeEditor();
		editor.setAsText(securityConfig.getUserDetails().getProperty(restUser));
		UserAttribute attribute = (UserAttribute) editor.getValue();
		log.info("User for downstream services = {}", restUser);
		return "Basic " + Base64.getEncoder().encodeToString((restUser + ":" + attribute.getPassword()).getBytes());
	}

	@Bean
	RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			Map<String, Collection<String>> headers = requestTemplate.headers();
			requestTemplate.headers(null);
			requestTemplate.headers(headers);
			requestTemplate.removeHeader(HttpHeaders.AUTHORIZATION); //Had to add this line to remove the original AUTHORIZATION header. Without this line, the requestTemplate tries to send both AUTHORIZATION header values at once, and it fails.
			requestTemplate.header(HttpHeaders.AUTHORIZATION, getAuthorizationHeader());
		};
	}

	@Bean
	@Primary
	AppFeignDecoder getAppFeignDecoder() {
		return new AppFeignDecoder();
	}
}