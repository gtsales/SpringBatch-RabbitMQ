package luiz.sales.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String cep;
	private String street;
	private String number;
	private String neighborhood;
	private String city;
	private String state;
}