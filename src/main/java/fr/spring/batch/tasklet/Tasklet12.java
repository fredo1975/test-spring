package fr.spring.batch.tasklet;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class Tasklet12 implements Tasklet {
	protected static final Logger log = Logger.getLogger(Tasklet12.class);
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		log.info("Tasklet12 execute");
		Thread.sleep(2000);
		return RepeatStatus.FINISHED;
	}

}
