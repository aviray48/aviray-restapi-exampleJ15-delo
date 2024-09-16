package ray.avi.example.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
//import io.awspring.cloud.messaging.core.SqsMessageHeaders;
//import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
//import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import lombok.extern.slf4j.Slf4j;
import ray.avi.common.vo.SimpleMessageObject;
import io.awspring.cloud.sqs.operations.SqsTemplate;

@Slf4j
@Component
public class MessageReceiptSenderToQueueImpl implements MessageReceiptSenderToQueue {

	public static final String SQS_GROUP_ID_HEADER = "message-group-id";
	public static final String SQS_DEDUPLICATION_ID_HEADER = "message-deduplication-id";
	
	public static final String MESSAGE_GROUP_ID = "create-message";

	//private QueueMessagingTemplate queueMessagingTemplate;
	private SqsTemplate sqsTemplate;
	
	@Value("${regs.comment.receipt.queue}")
	//@Value("${rs.c.receipt.queue}")
	private String sqsQueueName;

	@Autowired
	public MessageReceiptSenderToQueueImpl(SqsTemplate sqsTemplate) {
		this.sqsTemplate = sqsTemplate;
	}

	private Map<String, Object> getHeaders() {
		Map<String, Object> headers = new HashMap<>();
		headers.put(SQS_GROUP_ID_HEADER, MESSAGE_GROUP_ID);
		headers.put(SQS_DEDUPLICATION_ID_HEADER, UUID.randomUUID().toString());
		return headers;
	}

	@Override
	public void send(SimpleMessageObject simpleMessageObject) {
		log.debug("Message-Info: Sending Receipt to " + sqsQueueName + ": " + simpleMessageObject.getAdditionalInfo());
		//sqsTemplate.
		sqsTemplate.convertAndSend(sqsQueueName, simpleMessageObject, getHeaders());
	}
}
