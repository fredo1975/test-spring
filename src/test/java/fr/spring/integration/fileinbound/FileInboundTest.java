package fr.spring.integration.fileinbound;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@DirtiesContext
public class FileInboundTest extends AbstractJUnit4SpringContextTests{
	protected static final Logger log = Logger.getLogger(FileInboundTest.class);
	
	@Autowired
	@Qualifier("filesIn1")
	FileReadingMessageSource filesIn1;
	

	@Autowired
	@Qualifier("filesIn2")
	FileReadingMessageSource filesIn2;
	
	@Autowired
	@Qualifier("receiveChannel1")
	PollableChannel receiveChannel1;
	@Autowired
	@Qualifier("receiveChannel2")
	PollableChannel receiveChannel2;
	@Autowired
	@Qualifier("copyChannel")
	PollableChannel copyChannel;
	

	@Test
	public void fileInbondAdapterTest() throws InterruptedException{
		/*
		Message<File> msg;
		File file = new File("E:/in/in1/mysql-connector-java-5.1.40.jar");
		Message<File> mm = MessageBuilder.withPayload(new File("E:\\in\\in1\\test.txt")).build();
		copyChannel.send(MessageBuilder.withPayload(new File("E:\\in\\in1\\mysql-connector-java-5.1.40.jar")).build());
		
		*/
		
		Message<File> msg = (Message<File>) receiveChannel1.receive();
		//copyChannel.receive(100);
		/*
		while ((msg = filesIn1.receive()) != null) {
			//final Message<File> message = MessageBuilder.withPayload(msg.getPayload()).build();
			//receiveChannel.send(message);
			//receiveChannel.send(msg);
			//log.info("(msg.getPayload().toString()="+msg.getPayload().toString()+" msg.getHeaders()="+msg.getHeaders());
	    }*/
		
		Thread.sleep(15000);
		//log.info("copyChannel.receive().getPayload()="+copyChannel.receive().getPayload());
	}
}
