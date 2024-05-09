package ray.avi.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ray.avi.common.security.CustomAuthenticationEntryPoint;
import java.util.Collection;
import java.util.Vector;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableConfigurationProperties(SecurityConfig.class)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${customer.authentication.aggregator.contentSecurityPolicy:default-src 'self'; font-src 'self' https://fonts.gstatic.com; script-src 'unsafe-eval' 'unsafe-inline' 'self'; style-src 'unsafe-inline' 'self' https://fonts.googleapis.com}")
	private String contentSecurityPolicy;

	@Autowired
	SecurityConfig config;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/swagger-ui*", "/info", "/health").permitAll()
			.anyRequest()
			.fullyAuthenticated()
			.and()
			.httpBasic()
	    	.and()
	    	.csrf()
	    	.disable();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests()
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.authenticationEntryPoint(authenticationEntryPoint())
		;
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager inMemoryUserDetailsManager = null;
		Collection<UserDetails> users = new Vector<UserDetails>();
		for(Object username : config.getUserDetails().keySet()) {
			UserAttributeEditor editor = new UserAttributeEditor();
			editor.setAsText(config.getUserDetails().getProperty((String) username));
			UserAttribute attr = (UserAttribute) editor.getValue();
			users.add(User.withUsername((String) username).password(passwordEncoder().encode(attr.getPassword())).roles(attr.getAuthorities().iterator().next().getAuthority()).build());
		}
		inMemoryUserDetailsManager = new InMemoryUserDetailsManager(users);
		return inMemoryUserDetailsManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public CustomAuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

}
