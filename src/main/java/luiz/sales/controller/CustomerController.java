package luiz.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luiz.sales.api.CustomerApi;
import luiz.sales.dto.CustomerDto;
import luiz.sales.interfaces.RabbitMQservice;

@RestController
@RequestMapping("/customer")
public class CustomerController implements CustomerApi{

	@Autowired
	private RabbitMQservice rabbitMQservice;
	
	@Override
	public void publishMessage(CustomerDto customerDto) {
		
		rabbitMQservice.sendMessage(customerDto);
	}
}
