package fr.spring.file.copy.demo.test;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

public class WorkerThread implements Runnable {
	private static Logger logger = Logger.getLogger(WorkerThread.class);
	private String command;
	private MessageChannel inputChannel;
	private PollableChannel outputChannel;
	private int i;
	public WorkerThread(String command, MessageChannel inputChannel,PollableChannel outputChannel,int i) {
		super();
		this.command = command;
		this.inputChannel=inputChannel;
		this.outputChannel=outputChannel;
		this.i=i;
	}

	@Override
	public void run() {
		logger.info(Thread.currentThread().getName()+" Start sending on inputChannel  command = "+command);
		send();
        logger.info(Thread.currentThread().getName()+" End.");
        receive();
	}
	private void send() {
        inputChannel.send(new GenericMessage<String>(command));
    }
	private void receive() {
		Message<String> messageReceived = (Message<String>) outputChannel.receive(this.i);
		//Message<String> messageReceived = outputChannel.receive(this.i).getPayload();
		logger.info(messageReceived.getPayload());
    }
	@Override
    public String toString(){
        return this.command;
    }
}
