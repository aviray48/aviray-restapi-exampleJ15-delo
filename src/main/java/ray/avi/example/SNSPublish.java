package ray.avi.example;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import io.github.gilbertojrequena.bonsai_sns.server.BonsaiSnsEnvironment;
import io.github.gilbertojrequena.bonsai_sns.server.BonsaiSnsServer;
import io.github.gilbertojrequena.bonsai_sns.server.Subscription;
import io.github.gilbertojrequena.bonsai_sns.server.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

public class SNSPublish {


	private static final Logger logger = LoggerFactory.getLogger(SNSPublish.class);
	
    private static AmazonSNS snsClient;

	@Autowired(required = false)
	private AWSCredentialsProvider awsCredentialsProvider;
	
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
        snsClient.publish("arn:aws:sns:someRegion:123456789:name", "message");
        // assertions
    }
    
	public static void main(String[] args) {
		final String classMethodName = "SNSRun.main";
		logger.info("{}|Starting aviray-restapi-example-delo", classMethodName);
		int i = 1;
		int b = 2;
		setup();
		publishSomething();
	}
}

