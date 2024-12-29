package luiz.sales.utils;

import luiz.sales.dto.CustomerDto;
import luiz.sales.model.Address;
import luiz.sales.model.Customer;

public class ConvertUtils {

	public static Customer covertCustomerDtoToCustomer(CustomerDto customerDto) {
		
    	double moneyAmount = Double.parseDouble(customerDto.getMoneyAmount());
    	
    	int score = Integer.parseInt(customerDto.getScore());
    	
    	Address address = new Address(customerDto.getAddress().getCep(), customerDto.getAddress().getStreet(), customerDto.getAddress().getNumber(),
    			customerDto.getAddress().getNeighborhood(), customerDto.getAddress().getCity(), customerDto.getAddress().getState());
    	
    	Customer customer = new Customer(customerDto.getName(), customerDto.getEmail(), customerDto.getCpf(), address, moneyAmount, score);
		
		return customer;
	}
}
