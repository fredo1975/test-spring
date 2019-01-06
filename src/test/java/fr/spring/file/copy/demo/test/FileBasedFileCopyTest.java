package fr.spring.file.copy.demo.test;

import java.io.File;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

public class FileBasedFileCopyTest {
	///@Autowired
	//SourcePollingChannelAdapter filesIn;
	@Test
	public void testFileBasedCopy() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("/fileCopyDemo-file.xml", FileBasedFileCopyTest.class);
		//FileCopyDemoCommon.displayDirectories(context);
		Thread.sleep(5000);
	}
	
	@Test
	public void testFileBasedCopy2() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("/fileCopyDemo-file.xml", FileBasedFileCopyTest.class);
		MessageChannel filesIn = (MessageChannel) context.getBean("filesIn");
		Message<File> mm = MessageBuilder.withPayload(new File("E:/in/in1/spring-integration-samples/input/test1.txt")).build();
		filesIn.send(mm);
	}
}
