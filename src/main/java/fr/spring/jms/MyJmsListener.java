package fr.spring.jms;

import org.springframework.stereotype.Component;

@Component
public class MyJmsListener {
	/*
	@JmsListener(destination = AppConfig.QUEUE_NAME)
	public void handleMessage(String message) {// implicit message type conversion
		System.out.println("received: " + message);
	}*/
}
