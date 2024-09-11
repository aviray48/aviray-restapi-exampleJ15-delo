package ray.avi.example;

import io.github.gilbertojrequena.bonsai_sns.server.BonsaiSnsEnvironment;
import io.github.gilbertojrequena.bonsai_sns.server.BonsaiSnsServer;
import io.github.gilbertojrequena.bonsai_sns.server.Subscription;
import io.github.gilbertojrequena.bonsai_sns.server.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SNSRun {


	private static final Logger logger = LoggerFactory.getLogger(SNSRun.class);
	
    private static BonsaiSnsServer server;

    public static void setup() {
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
                                    .withEndpoint("http:/localhost:8080/endpoint")
                                    //.withEndpoint("http:/localhost:9324/queue1")
                                    .withProtocol("http")
                                    .withAttribute("a", "b")
                            )
                    )
            ).start();		
    }

    public static void shutdown() {
        server.stop();
    }

	public static void main(String[] args) {
		final String classMethodName = "SNSRun.main";
		logger.info("{}|Starting aviray-restapi-example-delo", classMethodName);
		int i = 1;
		int b = 2;
		setup();
		while (i != 0) {
			i = 1;
			b = 2;
		}
		shutdown();
	}
}

