package luiz.sales.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String cpf;
	private AddressDto address;
	private String moneyAmount;
	private String score; 
}
