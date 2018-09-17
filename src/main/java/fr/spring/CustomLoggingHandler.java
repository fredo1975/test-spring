package fr.spring;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.messaging.Message;

public class CustomLoggingHandler {
	protected static final Logger log = Logger.getLogger(CustomLoggingHandler.class);
	public void process(JobExecution jobExecution){
		
		if(ExitStatus.COMPLETED.equals(jobExecution.getExitStatus())){
			String jobName = jobExecution.getJobInstance().getJobName();
			log.info("**************** job="+jobName+" COMPLETED *******************");
		}
		
	}
	
	public void sayHello(Message<?> message){
		log.info("message.getPayload()="+message.getPayload()+" **** "+"message.getHeaders()="+message.getHeaders());
	}
}
