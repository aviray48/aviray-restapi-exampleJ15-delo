package ray.avi.example.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

@ConfigurationProperties(prefix="spring.security")
@Primary
public class SecurityConfig {
	Properties userDetails = new Properties();
	public Properties getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(Properties userDetails) {
		this.userDetails = userDetails;
	}
}