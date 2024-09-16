package ray.avi.example.config;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.awspring.cloud.sqs.operations.SqsTemplateBuilder;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.SnsAsyncClientBuilder;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClientBuilder;
import software.amazon.awssdk.services.sqs.SqsAsyncClientBuilder;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;


//import com.amazonaws.auth.AWSCredentialsProvider;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.sns.AmazonSNSAsync;
//import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
//import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
//import org.springframework.cloud.aws.core.region.RegionProvider;
//import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;

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
	 * Creates the AmazonSNSAsync Bean. 
	 * 
	 * @return the AmazonSNSAsync Bean
	 */
	@Lazy
	@Bean(destroyMethod = "shutdown")
	public SnsAsyncClient amazonSNS() throws Exception {

		SnsAsyncClientBuilder builder = SnsAsyncClient.builder();

		if (this.awsCredentialsProvider != null) {
			builder.withCredentials(this.awsCredentialsProvider);
		}
		
		if (this.s12vSNSEnabled) {
			String s12vUrl = "http://" + s12vSNSHost + ":" + s12vSNSPort;
			builder.endpointOverride(new URI(s12vUrl));
		}
		builder.region(Region.US_EAST_1);
		
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
