package fr.spring.integration.aggregator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:applicationContext-aggregator.xml"})
@DirtiesContext
public class AggreagatorTest extends AbstractJUnit4SpringContextTests{
	protected static final Logger log = Logger.getLogger(AggreagatorTest.class);
	@Autowired
	MessageChannel inputChannel;
	
	@Autowired
	QueueChannel testChannel;
	
	@Autowired
	MessageChannel outputChannel;
	
	@Test
	public void testOne() {
		inputChannel.send(MessageBuilder.withPayload("   a ,z  ").build());
		Message<?> outMessage = testChannel.receive(0);
		log.info("outMessage.getPayload()="+outMessage.getPayload());
		assertNotNull(outMessage);
		assertTrue(outMessage.getPayload().equals("A,Z"));
		outMessage = testChannel.receive(0);
		assertNull("Only one message expected", outMessage);
	}
}
