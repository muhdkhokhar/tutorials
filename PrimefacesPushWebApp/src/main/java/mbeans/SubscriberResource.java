package mbeans;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint(value = "/subscriber")
public class SubscriberResource {

	@OnMessage(encoders = { JSONEncoder.class })
	public String onMessage(String message) {
		return message;
	}

}
