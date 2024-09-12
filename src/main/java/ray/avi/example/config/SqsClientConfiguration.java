package ray.avi.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;

/**
 * SQS Client Configuration. Points to ElasticMQ if it is enabled.
 */
@Configuration
public class SqsClientConfiguration {

	/**
	 * The AWSCredentialsProvider
	 */
	@Autowired(required = false)
	private AWSCredentialsProvider awsCredentialsProvider;

	/**
	 * Flag indicating if ElasticMQ is enabled. By default this is false.
	 */
	@Value("${elasticmq.enabled:false}")
	private boolean elasticMqEnabled;

	/**
	 * The Elastic MQ Host. By default this is 'localhost'
	 */
	@Value("${elasticmq.host:localhost}")
	private String elasticMqHost;

	/**
	 * The Elastic MQ Port. By default this is 9324
	 */
	@Value("${elasticmq.port:9324}")
	private String elasticMqPort;

	/**
	 * The Region Provider
	 */
	@Autowired(required = false)
	private RegionProvider regionProvider;

	/**
	 * The SQS Listener Max Number of messages to process at once. By default
	 * this is 5. This number cannot be greater than 10.
	 */
	@Value("${regs.sqs.listener.max.messages:5}")
	private int sqsListenerMaxNumberOfMessages;

	/**
	 * The SQS Listener Visibility Timeout. By default this is 5 minutes (300
	 * seconds)
	 */
	@Value("${regs.sqs.listener.visibility.timeout:300}")
	private int sqsListenerVisiblityTimeout;

	/**
	 * Creates the AmazonSQSAsync Bean. Overriding the default AWS Bean config
	 * so that: <br/>
	 * 1. We can locally point to ElasticMQ if it is enabled. <br/>
	 * 2. Support FIFO queues. The default AmazonSQSAsyncClient loaded in the
	 * autoconfig is not supported by FIFO queues.
	 * 
	 * @return the AmazonSQSAsync Bean
	 */
	@Lazy
	@Bean(destroyMethod = "shutdown")
	public AmazonSQSAsync amazonSQS() throws Exception {

		AmazonSQSAsyncClientBuilder builder = AmazonSQSAsyncClientBuilder.standard();

		if (this.awsCredentialsProvider != null) {
			builder.withCredentials(this.awsCredentialsProvider);
		}

		if (this.elasticMqEnabled) {
			String elasticMqUrl = "http://" + elasticMqHost + ":" + elasticMqPort;
			builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(elasticMqUrl, "us-east-1"));
		} else if (this.regionProvider != null) {
			builder.withRegion(this.regionProvider.getRegion().getName());
		} else {
			builder.withRegion(Regions.DEFAULT_REGION);
		}

		return builder.build();
	}

	/**
	 * Returns the QueueMessagingTemplate
	 * 
	 * @param amazonSqsClient
	 *            the AmazonSQSAsync client
	 * @return the QueueMessagingTemplate
	 */
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSqsClient) {
		return new QueueMessagingTemplate(amazonSqsClient);
	}

	/**
	 * Override the default SimpleMessageListenerContainerFactory so that we can
	 * set the max number of messages and visibility timeout.<br/>
	 * The visibility timeout is important because Documentum objects can
	 * sometimes take minutes to create. If the creation has not been
	 * completed between polling, the listener will pick up the message again
	 * and the document can be processed multiple times. Usually this is only an
	 * issue when the application first starts up, as the BOF will be
	 * downloaded, agency config will load, etc.
	 * 
	 * @param amazonSqs
	 *            AmazonSQSAsync
	 * @return the SimpleMessageListenerContainerFactory
	 */
	@Bean
	public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
		SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
		factory.setAmazonSqs(amazonSqs);
		factory.setMaxNumberOfMessages(sqsListenerMaxNumberOfMessages);
		factory.setVisibilityTimeout(sqsListenerVisiblityTimeout);
		return factory;
	}
}
