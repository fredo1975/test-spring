package fr.spring.file.copy.demo.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FileBasedFileCopyTest {
	@Test
	public void testFileBasedCopy() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("/fileCopyDemo-file.xml", FileBasedFileCopyTest.class);
		//FileCopyDemoCommon.displayDirectories(context);
		Thread.sleep(5000);
	}
}
