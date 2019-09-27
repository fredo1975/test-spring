package fr.spring.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class MyOtherJmsListener implements MessageListener {
	private static Logger logger = Logger.getLogger(MyOtherJmsListener.class);

	@Override
	public void onMessage(Message message) {
		try {
			logger.info("debut reception message");
			TextMessage msg = (TextMessage) message;
			logger.info(" Message recu : " + msg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		logger.info("fin reception message");
	}

}
