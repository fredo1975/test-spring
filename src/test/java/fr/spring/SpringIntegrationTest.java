package fr.spring;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
@DirtiesContext
public class SpringIntegrationTest extends AbstractJUnit4SpringContextTests{
	protected static final Logger log = Logger.getLogger(SpringIntegrationTest.class);
	
	private DirectChannel fixedDelayChannel;
	public void setFixedDelayChannel(DirectChannel fixedDelayChannel) {
		this.fixedDelayChannel = fixedDelayChannel;
	}
	private DirectChannel fixedDelayChannel2;
	public void setFixedDelayChannel2(DirectChannel fixedDelayChannel2) {
		this.fixedDelayChannel2 = fixedDelayChannel2;
	}
	private PublishSubscribeChannel fixedDelayChannel3;
	public void setFixedDelayChannel3(PublishSubscribeChannel fixedDelayChannel3) {
		this.fixedDelayChannel3 = fixedDelayChannel3;
	}
	DirectChannel outputChannel;
	public void setOutputChannel(DirectChannel outputChannel) {
		this.outputChannel = outputChannel;
	}
	
	@Autowired
	MessageChannel inputChannel;
	
	@Autowired
	QueueChannel testChannel;
	
	@Test
	public void PollMessageTest() throws InterruptedException{
		//fixedDelayChannel.start();
		Thread.sleep(5000);
		//fixedDelayChannel.stop();
	}
	
	@Test
	public void testOne() {
		inputChannel.send(MessageBuilder.withPayload("   a   ").build());
		Message<?> outMessage = testChannel.receive(0);
		assertNotNull(outMessage);
		assertTrue(outMessage.getPayload().equals("A"));
		outMessage = testChannel.receive(0);
		assertNull("Only one message expected", outMessage);
	}

}
