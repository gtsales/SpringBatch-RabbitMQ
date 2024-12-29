package luiz.sales.services;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.amqp.AmqpItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import luiz.sales.constants.RabbitMQConstants;
import luiz.sales.dto.CustomerDto;
import luiz.sales.interfaces.RabbitMQservice;
import luiz.sales.model.Customer;

@Service
@Slf4j
public class RabbitMQServiceImpl implements RabbitMQservice{
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public ItemReader<CustomerDto> receiveMessage() {
		
		log.debug("Receiving message process started");
		
		rabbitTemplate.receive();
		
		ItemReader<CustomerDto> customerDto = new AmqpItemReader<>(rabbitTemplate);
		
		log.debug("Receiving message process finished");
		
		return customerDto;
	}

	@Override
	public void sendMessage(CustomerDto customerDto) {
		
		log.debug("Seding object to queue {}", customerDto);
		
		try {
			
			if(customerDto.getCpf() != null && !customerDto.getCpf().isEmpty()) {
				
				rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_DEFAULT_NAME, RabbitMQConstants.ROUTING_KEY_DEFAULT, customerDto);
			} else {
				
				log.error("Order must have cpf registered");
			}
			
		} catch (AmqpException e) {
			
			e.printStackTrace();
		}
		
		log.debug("Object was sent to queue {}", customerDto);
	}

	@Override
	public void sendMessageCustomer(Customer customer) {
		
		log.debug("Seding object to queue {}", customer);
		
		try {
			
			if(customer.getCpf() != null && !customer.getCpf().isEmpty()) {
				
				rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_ABLE_NAME, RabbitMQConstants.ROUTING_KEY_ABLE_CUSTOMER, new Gson().toJson(customer));
			} else {
				
				log.error("Order must have cpf registered");
			}
			
		} catch (AmqpException e) {
			
			e.printStackTrace();
		}
		
		log.debug("Object was sent to queue {}", customer);
	}
}
