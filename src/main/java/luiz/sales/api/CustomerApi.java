package luiz.sales.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import luiz.sales.dto.CustomerDto;

public interface CustomerApi {

	@PostMapping("/publish")
	void publishMessage(@RequestBody CustomerDto customerDto);
}
