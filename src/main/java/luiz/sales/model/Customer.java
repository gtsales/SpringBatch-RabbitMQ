package luiz.sales.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Able_Customers")
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	
	@Id
	private String cpf;
	private Address address;
	private double moneyAmount;
	private int score; 
}
