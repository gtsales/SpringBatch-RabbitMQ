package luiz.sales.interfaces;

import org.springframework.batch.item.ItemReader;

import luiz.sales.dto.CustomerDto;
import luiz.sales.model.Customer;

public interface RabbitMQservice {

	ItemReader<CustomerDto> receiveMessage();
	void sendMessage(CustomerDto customerDto);
	void sendMessageCustomer(Customer customer);
}
