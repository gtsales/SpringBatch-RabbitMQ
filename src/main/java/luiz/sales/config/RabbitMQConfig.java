package luiz.sales.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import luiz.sales.constants.RabbitMQConstants;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.listener.max.concurrent.consumers}")
	private int listenerMaxConcurrentConsumers;
	
	@Value("${rabbitmq.listener.min.concurrent.consumers}")
	private int listenerMinConcurrentConsumers;
	
	@Bean(name = "queueDefaultCustomer")
	@Primary
	Queue queueDefaultCustomer() {
		
		return new Queue(RabbitMQConstants.DEFAULT_CUSTOMER_QUEUE_NAME, true);
	}
	
	@Bean(name = "defaultCustomerExchange")
	DirectExchange defaultCustomerExchange() {
		
		return new DirectExchange(RabbitMQConstants.EXCHANGE_DEFAULT_NAME, true, false);
	}
	
	@Bean(name = "bindingDefault")
	Binding bindingDefault(@Qualifier("queueDefaultCustomer") Queue queue, @Qualifier("defaultCustomerExchange") DirectExchange directExchange) {
		
		 return BindingBuilder.bind(queue).to(directExchange).with(RabbitMQConstants.ROUTING_KEY_DEFAULT);
	}
	
	@Bean(name = "queueAbleCustomer")
	Queue queueAbleCustomer() {
		
		return new Queue(RabbitMQConstants.ABLE_CUSTOMER_QUEUE_NAME, true);
	}
	
	@Bean(name = "ableCustomerExchange")
	DirectExchange ableCustomerExchange() {
		
		return new DirectExchange(RabbitMQConstants.EXCHANGE_ABLE_NAME, true, false);
	}
	
	@Bean(name = "bindingAble")
	Binding bindingAble(@Qualifier("queueAbleCustomer") Queue queue, @Qualifier("ableCustomerExchange") DirectExchange directExchange) {
		
		 return BindingBuilder.bind(queue).to(directExchange).with(RabbitMQConstants.ROUTING_KEY_ABLE_CUSTOMER);
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

		container.setConnectionFactory(connectionFactory);
		container.setConcurrentConsumers(listenerMinConcurrentConsumers);
		container.setMaxConcurrentConsumers(listenerMaxConcurrentConsumers);
		
		return container;
	}
	
	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		
	    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    
	    rabbitTemplate.setDefaultReceiveQueue(RabbitMQConstants.DEFAULT_CUSTOMER_QUEUE_NAME);
	    return rabbitTemplate;
	}
}
