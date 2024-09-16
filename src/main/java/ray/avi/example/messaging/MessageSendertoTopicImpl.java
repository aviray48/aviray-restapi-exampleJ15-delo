package ray.avi.example.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
//import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import ray.avi.common.vo.SimpleMessageObject;

/**
 * Sender class. Used to send messages to the SNS Topic
 */
@Slf4j
@Component
public class MessageSendertoTopicImpl implements MessageSendertoTopic {

	private NotificationMessagingTemplate notificationMessagingTemplate;

	@Value("${regs.comment.created.sns}")
	//@Value("${rs.c.created.sns}")
	private String snsTopicName;

	@Autowired
	public MessageSendertoTopicImpl(NotificationMessagingTemplate notificationMessagingTemplate) {
		this.notificationMessagingTemplate = notificationMessagingTemplate;
	}
	
	public void send(SimpleMessageObject simpleMessageObject) {
		log.debug("Message-Info: Sending to " + snsTopicName + ": " + simpleMessageObject.getAdditionalInfo());
		notificationMessagingTemplate.convertAndSend(snsTopicName, simpleMessageObject);
	}
}
