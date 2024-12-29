package luiz.sales.services;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import luiz.sales.interfaces.VerifyEligibilityService;
import luiz.sales.model.Customer;

@Service
@Slf4j
public class VerifyEligibilityServiceImpl implements VerifyEligibilityService{

	@Override
	public boolean verifyEligibility(Customer customer) {
		
		log.info("Starting process to verify eligibility. NAME - {} - CPF {} - SCORE {} - MONEY_AMOUNT {}", customer.getName(), customer.getCpf(), customer.getScore(), customer.getMoneyAmount());
		
		boolean isEligible;
		
		if (customer != null && customer.getScore() >= 700 && customer.getMoneyAmount() >= 10.000) {
			
			isEligible = true;
			
			return isEligible;
		}
		
		isEligible = false;
		
		log.info("Starting process to verify eligibility. IS_ELIGIBLE {} - NAME - {} - CPF {} - SCORE {} - MONEY_AMOUNT {}", isEligible, customer.getName(), customer.getCpf(), customer.getScore(), customer.getMoneyAmount());
		
		return isEligible;
	}
}
