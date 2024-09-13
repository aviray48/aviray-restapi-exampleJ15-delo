package ray.avi.example.messaging;

import ray.avi.common.vo.SimpleMessageObject;

public interface SentMessageQueueMessageReceiver {

	void receiveMessage(SimpleMessageObject simpleMessageObject);
	void receiveMessage(CommentDetailsModel commentDetailsModel);

}
