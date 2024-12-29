package luiz.sales.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import luiz.sales.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{

}
