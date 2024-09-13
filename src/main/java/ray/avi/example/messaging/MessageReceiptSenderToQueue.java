package ray.avi.example.messaging;

import ray.avi.common.vo.SimpleMessageObject;

public interface MessageReceiptSenderToQueue {

	void send(SimpleMessageObject simpleMessageObject);

}
