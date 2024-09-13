package ray.avi.example.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import lombok.extern.slf4j.Slf4j;
import ray.avi.common.vo.SimpleMessageObject;

@Slf4j
@Component
public class MessageReceiptSenderToQueueImpl implements MessageReceiptSenderToQueue {

	public static final String MESSAGE_GROUP_ID = "create-message";

	private QueueMessagingTemplate queueMessagingTemplate;

	//@Value("${regs.comment.receipt.queue}")
	@Value("${rs.c.receipt.queue}")
	private String sqsQueueName;

	@Autowired
	public MessageReceiptSenderToQueueImpl(QueueMessagingTemplate queueMessagingTemplate) {
		this.queueMessagingTemplate = queueMessagingTemplate;
	}

	private Map<String, Object> getHeaders() {
		Map<String, Object> headers = new HashMap<>();
		headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, MESSAGE_GROUP_ID);
		headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, UUID.randomUUID().toString());
		return headers;
	}

	@Override
	public void send(SimpleMessageObject simpleMessageObject) {
		log.debug("Message-Info: Sending Receipt to " + sqsQueueName + ": " + simpleMessageObject.getAdditionalInfo());
		queueMessagingTemplate.convertAndSend(sqsQueueName, simpleMessageObject, getHeaders());
	}
}
