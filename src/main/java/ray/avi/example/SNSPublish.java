package ray.avi.example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SNSPublish {


	private static final Logger logger = LoggerFactory.getLogger(SNSPublish.class);
	
    private static AmazonSNS snsClient;

    public static void setup() {
		AmazonSNSAsyncClientBuilder builder = AmazonSNSAsyncClientBuilder.standard();
		builder.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("accessKey", "secretKey")));
		//builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:7979", "us-east-1"));
		builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:9911", "us-east-1"));
		snsClient =  builder.build();
		
    }

    public static void publishSomething() {
        //snsClient.publish("arn:aws:sns:us-east-1:123456789:test-topic-01", "message");
        //snsClient.publish("arn:aws:sns:us-east-1:123456789:rs-dev-c-created", "message");
        snsClient.publish("arn:aws:sns:us-east-1:123456789:test-topic-01", "{'sample':'SendToQueuefromTopicViaEclipse'}");
    }
    
	public static void main(String[] args) {
		final String classMethodName = "SNSPublish.main";
		logger.info("{}|Starting aviray-restapi-example-delo", classMethodName);
		setup();
		publishSomething();
	}
}

