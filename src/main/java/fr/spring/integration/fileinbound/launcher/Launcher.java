package fr.spring.integration.fileinbound.launcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-fileInbondAdapter.xml",
				"applicationContext-batch.xml",
				"applicationContext.xml");
	}

}
