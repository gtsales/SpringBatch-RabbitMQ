package luiz.sales.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class JobScheduler {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job receiveMessageJob;
	
	@Scheduled(fixedDelay = 5000)
	void startJob() {
		
		String randomValue = new RunIdIncrementer().toString();
		
		try {
			
			jobLauncher.run(receiveMessageJob, new JobParametersBuilder().addString("receiveMessageJob", randomValue).toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			
			e.printStackTrace();
		}
	}
}
