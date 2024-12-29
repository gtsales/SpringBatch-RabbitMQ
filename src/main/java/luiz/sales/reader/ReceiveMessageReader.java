package luiz.sales.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import luiz.sales.dto.CustomerDto;
import luiz.sales.interfaces.RabbitMQservice;

@Slf4j
@Component
@StepScope
public class ReceiveMessageReader implements ItemReader<CustomerDto>{

	@Autowired
	private RabbitMQservice rabbitMQservice;
	
	@Override
	public CustomerDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		ItemReader<CustomerDto> customerDto = null;
		
		try {
			
			customerDto = rabbitMQservice.receiveMessage();
			
		} catch (Exception e) {
			
			log.error("Something went wrong when trying to read the object [{}]", e.getMessage());
		}
		
		return customerDto.read();
	}
}
