package fr.spring.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

@Configuration
@ComponentScan
@EnableJms
public class AppConfig {
	public static final String QUEUE_NAME = "test.queue";

	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		return connectionFactory;
	}

	@Bean
	public JmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		// core poll size=4 threads and max poll size 8 threads
		factory.setConcurrency("4-8");
		return factory;
	}

	@Bean
	public MessageListenerContainer listenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setDestinationName(QUEUE_NAME);
		container.setMessageListener(new MyOtherJmsListener());
		return container;
	}

	public static void main(String[] args) throws InterruptedException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		MyMessageSender ms = context.getBean(MyMessageSender.class);
		ms.sendMessage("test message 1");
		ms.sendMessage("test message 2");

		/*
		 * System.out.println("-- shutting down listener container --");
		 * JmsListenerEndpointRegistry bean =
		 * context.getBean(JmsListenerEndpointRegistry.class); for
		 * (MessageListenerContainer listenerContainer : bean.getListenerContainers()) {
		 * DefaultMessageListenerContainer container = (DefaultMessageListenerContainer)
		 * listenerContainer; container.shutdown(); }
		 */
		//DefaultMessageListenerContainer container = context.getBean(DefaultMessageListenerContainer.class);
		//container.shutdown();
	}
}
