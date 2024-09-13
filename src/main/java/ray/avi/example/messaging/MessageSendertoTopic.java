package ray.avi.example.messaging;

import ray.avi.common.vo.SimpleMessageObject;

public interface MessageSendertoTopic {

	void send(SimpleMessageObject simpleMessageObject);

}
