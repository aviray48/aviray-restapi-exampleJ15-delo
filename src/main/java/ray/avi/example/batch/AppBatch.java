package ray.avi.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.cloud.openfeign.EnableFeignClients;

import ray.avi.example.batch.internal.BatchInternal;
import ray.avi.example.config.SecurityConfig;

@Profile({"batchExample", "batchExampleSimpleBatch"})
@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({SecurityConfig.class})
public class AppBatch {


	private static final Logger logger = LoggerFactory.getLogger(AppBatch.class);

	public static void main(String[] args) {
		final String classMethodName = "AppBatch.main";
		logger.info("{}|Starting example-app-batch", classMethodName);
		System.exit(SpringApplication.exit(SpringApplication.run(AppBatch.class, args)));
		
	}
	
	@Bean
	public BatchInternal BatchInternalRun(String[] args) {
	    return new BatchInternal(args);
	}

}
