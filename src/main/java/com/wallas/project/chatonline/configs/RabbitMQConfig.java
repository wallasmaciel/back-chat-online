package com.wallas.project.chatonline.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMQConfig {
	@Bean
	ConnectionFactory connectionFactory() {
		ConnectionFactory factory = new ConnectionFactory();
	    // "guest"/"guest" by default, limited to localhost connections
	    factory.setUsername("guest");
	    factory.setPassword("guest");
	    factory.setVirtualHost("/");
	    factory.setHost("localhost");
	    factory.setPort(5672);
	    return factory;
    }
}
