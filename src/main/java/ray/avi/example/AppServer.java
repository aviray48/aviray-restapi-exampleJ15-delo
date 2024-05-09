package ray.avi.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ray.avi.example.config.SecurityConfig;

@Profile("web")
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({SecurityConfig.class})
public class AppServer {

	private static final Logger logger = LoggerFactory.getLogger(AppServer.class);

	public static void main(String[] args) {
		final String classMethodName = "AppServer.main";
		logger.info("{}|Starting aviray-restapi-example-delo", classMethodName);
		SpringApplication.run(AppServer.class, args);
	}

}
 