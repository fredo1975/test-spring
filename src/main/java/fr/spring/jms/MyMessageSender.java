package fr.spring.jms;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MyMessageSender {
	private static Logger logger = Logger.getLogger(MyMessageSender.class);
	@Autowired
	private ConnectionFactory connectionFactory;
	private JmsTemplate jmsTemplate;

	@PostConstruct
	public void init() {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
	}

	public void sendMessage(String message) {
		logger.info("sending: " + message);
		jmsTemplate.send(AppConfig.QUEUE_NAME, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
}
