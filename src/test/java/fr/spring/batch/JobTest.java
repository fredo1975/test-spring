package fr.spring.batch;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml",
		"classpath*:test-context.xml" })
public class JobTest {

	protected static final Logger log = Logger.getLogger(JobTest.class);

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils1;
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils2;
	
	/*
	private JobLauncherTestUtils jobLauncherTestUtils2;
	public void setJobLauncherTestUtils2(JobLauncherTestUtils jobLauncherTestUtils2) {
		this.jobLauncherTestUtils2 = jobLauncherTestUtils2;
	}*/
	
	@Test
	public void launchJob() throws Exception {

		// testing a job
		JobExecution jobExecution1 = jobLauncherTestUtils1.launchJob();
		JobExecution jobExecution2 = jobLauncherTestUtils2.launchJob();
		log.info("test");
		// Testing an individual step
		//JobExecution jobExecution = jobLauncherTestUtils.launchStep("readWriteFilm");

		assertEquals(BatchStatus.COMPLETED, jobExecution1.getStatus());
		assertEquals(BatchStatus.COMPLETED, jobExecution2.getStatus());

	}
}
