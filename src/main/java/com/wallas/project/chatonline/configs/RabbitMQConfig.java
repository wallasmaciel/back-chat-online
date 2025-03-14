package com.wallas.project.chatonline.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMQConfig {
	private ConnectionFactory connectionFactory;
	@Bean
	ConnectionFactory connectionFactory() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		    // "guest"/"guest" by default, limited to localhost connections
			connectionFactory.setUsername("guest");
			connectionFactory.setPassword("guest");
			connectionFactory.setVirtualHost("/");
			connectionFactory.setHost("rabbitmq");
			connectionFactory.setPort(5672);
		}
		// 
	    return connectionFactory;
    }
}
