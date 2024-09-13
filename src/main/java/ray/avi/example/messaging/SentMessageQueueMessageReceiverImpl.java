package ray.avi.example.messaging;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;
import ray.avi.common.vo.SimpleMessageObject;

@Slf4j
@Component
public class SentMessageQueueMessageReceiverImpl implements SentMessageQueueMessageReceiver {

	private MessageReceiptSenderToQueue messageReceiptSenderToQueue;
	private MessageSendertoTopic messageSendertoTopic;

	@Autowired
	public SentMessageQueueMessageReceiverImpl(MessageReceiptSenderToQueue messageReceiptSenderToQueue, MessageSendertoTopic messageSendertoTopic) {
		this.messageReceiptSenderToQueue = messageReceiptSenderToQueue;
		this.messageSendertoTopic = messageSendertoTopic;
	}
	
	@Value("${rs.submit.c.queue}")
	private String rsSubmitCQueue;
	
	@Bean
	String getRsSubmitCQueue() {
		String rsSubmitCQueueInternal = rsSubmitCQueue;
		log.info("rs.submit.c.queue = {}", rsSubmitCQueueInternal);
		return rsSubmitCQueueInternal;
	}
	
	//@SqsListener("${rs.submit.c.queue}")
	public void receiveMessage(SimpleMessageObject simpleMessageObject) {
		logMessageDetailsModel(simpleMessageObject);
		messageReceiptSenderToQueue.send(simpleMessageObject);
		messageSendertoTopic.send(simpleMessageObject);
	}


	private void logMessageDetailsModel(SimpleMessageObject simpleMessageObject) {
		log.info("Message-Info: " + simpleMessageObject.getAdditionalInfo());
	}

	@SqsListener("${rs.submit.c.queue}")
	public void receiveMessage(CommentDetailsModel commentDetailsModel) {
		logMessageDetailsModel(commentDetailsModel);
		//messageReceiptSenderToQueue.send(commentDetailsModel);
		//messageSendertoTopic.send(commentDetailsModel);
	}
	
	private void logMessageDetailsModel(CommentDetailsModel commentDetailsModel) {
		log.info("Message-Info: " + commentDetailsModel.getCommentTrackingNumber());
	}
}
