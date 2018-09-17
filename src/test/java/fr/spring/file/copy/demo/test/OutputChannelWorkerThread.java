package fr.spring.file.copy.demo.test;

import org.apache.log4j.Logger;
import org.springframework.messaging.PollableChannel;

public class OutputChannelWorkerThread implements Runnable {
	private static Logger logger = Logger.getLogger(OutputChannelWorkerThread.class);
	private int command;
	PollableChannel outputChannel;
	
	public OutputChannelWorkerThread(int command,PollableChannel outputChannel) {
		super();
		this.command = command;
		this.outputChannel=outputChannel;
	}

	@Override
	public void run() {
		logger.info(Thread.currentThread().getName()+" Start receiving on outputChannel  command = "+command);
        processCommand();
        logger.info(Thread.currentThread().getName()+" End.");
	}
	private void processCommand() {
		outputChannel.receive(this.command).getPayload();
    }
	
}
