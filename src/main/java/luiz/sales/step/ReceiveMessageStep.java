package luiz.sales.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import luiz.sales.dto.CustomerDto;
import luiz.sales.reader.ReceiveMessageReader;
import luiz.sales.writer.ReceiveMessageWriter;

@Configuration
public class ReceiveMessageStep {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	ReceiveMessageReader reader() {
		
		return new ReceiveMessageReader();
	}
	
	@Bean
	ReceiveMessageWriter writer() {
	
		return new ReceiveMessageWriter();
	}
	
	@Bean
	Step receiveMessageStepQueue() {
		
		return stepBuilderFactory
				.get("receiveMessageStepQueue")
				.<CustomerDto, CustomerDto>chunk(10)
				.reader(reader())
				.writer(writer())
				.build();
	}
}
