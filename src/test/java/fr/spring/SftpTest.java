package fr.spring;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.endpoint.SourcePollingChannelAdapter;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.jcraft.jsch.JSchException;

@ContextConfiguration(locations={"classpath:applicationContext-sftpInbondAdapter.xml"})
@DirtiesContext
public class SftpTest extends AbstractJUnit4SpringContextTests{
	protected static final Logger log = Logger.getLogger(SftpTest.class);
	@Autowired
	private DefaultSftpSessionFactory sftpSessionFactory;
	@Autowired
	PollableChannel receiveChannel;
	@Autowired
	SourcePollingChannelAdapter sftpInbondAdapter;
	
	@Test
	public void sftpInbondAdapterTest() throws JSchException{
		//sftpSessionFactory.getSession();
		sftpInbondAdapter.start();
		Message<?> received = receiveChannel.receive();
		assertNotNull("Expected file", received);
		System.out.println("Received first file message: " + received);
		Message<?> received2 = receiveChannel.receive();
		assertNotNull("Expected file", received2);
		System.out.println("Received second file message: " + received2);
		sftpInbondAdapter.stop();
	}
	
}