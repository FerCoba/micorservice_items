package com.springboot.microservice.microservices_item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
//	@LoadBalanced (Balanceador de Ribbon, en este caso esta comentado porque eureka lo maneja automaticamente)
	public RestTemplate clientRestTemplate() {
		return new RestTemplate();
	}
}
