package fr.spring.batch.custom.reader.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml",
		"classpath*:applicationContext-custom-file-reader.xml" })
public class CustomReaderTest {
	private static Logger logger = Logger.getLogger(CustomReaderTest.class);
	@Autowired
	@Qualifier("fileReceiveChannel")
	MessageChannel fileReceiveChannel;
	
	@Test
	public void testCustomReader() throws IOException {
		ClassPathResource cp = new ClassPathResource("custom-reader.txt");
		File file = cp.getFile();
		boolean send = fileReceiveChannel.send(MessageBuilder.withPayload(file).build());
		assertTrue(send);
		
	}
}
