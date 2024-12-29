package luiz.sales.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ReceiveMessageJob {

	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Bean
	Job receiveMessageJobQueue(Step receiveMessageStepQueue) {
		
		return jobBuilderFactory
				.get("receiveMessageJob")
				.start(receiveMessageStepQueue)
				.build();
	}
}
