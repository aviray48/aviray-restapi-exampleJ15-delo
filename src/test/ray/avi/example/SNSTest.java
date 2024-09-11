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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SNSTest {

    private BonsaiSnsServer server;
    private AmazonSNS snsClient;

	@Autowired(required = false)
	private AWSCredentialsProvider awsCredentialsProvider;
	
    @Before
    public void setup() {
        // start your app at 8080

        server = new BonsaiSnsServer.Builder()
            .withAccountId("123456789")
            .withPort(7979)
            .withRegion("someRegion")
            .withBonsaiSnsEnvironmentDefinition(
                BonsaiSnsEnvironment.Companion.definition()
                    .withTopic(
                        Topic.Companion.definition()
                            .withName("name")
                            .withSubscription(
                                Subscription.Companion.definition()
                                    //.withEndpoint("http:/localhost:8080/endpoint")
                                    .withEndpoint("http:/localhost:9324/queue1")
                                    .withProtocol("http")
                                    .withAttribute("a", "b")
                            )
                    )
            ).start();

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

    @After
    public void shutdown() {
        server.stop();
    }

    @Test
    public void testSomething() {
        snsClient.publish("arn:aws:sns:someRegion:123456789:name", "message");
        // assertions
    }
}

