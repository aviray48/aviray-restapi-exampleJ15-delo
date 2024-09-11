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
        // start your app at 8080

		AmazonSNSAsyncClientBuilder builder = AmazonSNSAsyncClientBuilder.standard();
		/*
		if (this.awsCredentialsProvider != null) {
			builder.withCredentials(this.awsCredentialsProvider);
		}
		*/
		builder.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("accessKey", "secretKey")));
		builder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:7979", "someRegion"));
		snsClient =  builder.build();
		
    }

    public static void publishSomething() {
        //snsClient.publish("arn:aws:sns:someRegion:123456789:name", "message");
        snsClient.publish("arn:aws:sns:someRegion:123456789:regs-dev-comment-created", "message");
        // assertions
    }
    
	public static void main(String[] args) {
		final String classMethodName = "SNSPublish.main";
		logger.info("{}|Starting aviray-restapi-example-delo", classMethodName);
		setup();
		publishSomething();
	}
}

