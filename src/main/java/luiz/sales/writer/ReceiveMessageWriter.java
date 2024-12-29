package luiz.sales.writer;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;

import lombok.extern.slf4j.Slf4j;
import luiz.sales.dto.CustomerDto;
import luiz.sales.interfaces.RabbitMQservice;
import luiz.sales.interfaces.VerifyEligibilityService;
import luiz.sales.model.Customer;
import luiz.sales.repository.CustomerRepository;
import luiz.sales.utils.ConvertUtils;

@Component
@StepScope
@Slf4j
public class ReceiveMessageWriter implements ItemWriter<CustomerDto>{

	@Autowired
	private VerifyEligibilityService verifyEligibilityService;
	
	@Autowired
	private RabbitMQservice rabbitMQservice;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void write(List<? extends CustomerDto> customersDto) throws Exception {
		
		Customer customer = null;
		
		for (CustomerDto customerDto : customersDto) {
			
			try {
				
				customer = ConvertUtils.covertCustomerDtoToCustomer(customerDto);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			if (customer !=null && verifyEligibilityService.verifyEligibility(customer)) {
				
				try {
					
					customerRepository.insert(customer);
				} catch (MongoException e) {
					
					log.error("Something went wrong trying to save customer {}", customer);
				}
				
				rabbitMQservice.sendMessageCustomer(customer);
			}
		}
	}
}
