package ray.avi.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;

import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;

/**
 * SNS Client Configuration. 
 */
@Configuration
public class SnsClientConfiguration {

	/**
	 * The AWSCredentialsProvider
	 */
	@Autowired(required = false)
	private AWSCredentialsProvider awsCredentialsProvider;

	/**
	 * Flag indicating if ElasticMQ is enabled. By default this is false.
	 */
	@Value("${s12vSNS.enabled:false}")
	private boolean s12vSNSEnabled;

	/**
	 * The Elastic MQ Host. By default this is 'localhost'
	 */
	@Value("${s12vSNS.host:localhost}")
	private String s12vSNSHost;

	/**
	 * The Elastic MQ Port. By default this is 9324
	 */
	@Value("${s12vSNS.port:9911}")
	private String s12vSNSPort;

	/**
	 * The Region Provider
	 */
	@Autowired(required = false)
	private RegionProvider regionProvider;

	
	/**
	 * Creates the AmazonSNSAsync Bean. 
	 * 
	 * @return the AmazonSNSAsync Bean
	 */
	@Lazy
	@Bean(destroyMethod = "shutdown")
	public AmazonSNSAsync amazonSNS() throws Exception {

		AmazonSNSAsyncClientBuilder builder = AmazonSNSAsyncClientBuilder.standard();

		if (this.awsCredentialsProvider != null) {
			builder.withCredentials(this.awsCredentialsProvider);
		}
		
		if (this.s12vSNSEnabled) {
			String elasticMqUrl = "http://" + s12vSNSHost + ":" + s12vSNSPort;
			builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(elasticMqUrl, "us-east-1"));
		} else if (this.regionProvider != null) {
			builder.withRegion(this.regionProvider.getRegion().getName());
		} else {
			builder.withRegion(Regions.DEFAULT_REGION);
		}
		
		return builder.build();
	}
	
	/**
	 * Returns the NotificationMessagingTemplate
	 * 
	 * @param amazonSnsClient
	 *            the AmazonSNSAsync client
	 * @return the NotificationMessagingTemplate
	 */
	@Bean
	public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNSAsync amazonSnsClient) {
		return new NotificationMessagingTemplate(amazonSnsClient);
	}

}
