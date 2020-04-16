package com.springboot.microservice.microservices_item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.springboot.microservice.servicec_commons.model.entities"})
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class MicroserviceItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceItemApplication.class, args);
	}

	@Bean
	DispatcherServlet dispatcherServlet () {
	    DispatcherServlet ds = new DispatcherServlet();
	    ds.setThrowExceptionIfNoHandlerFound(true);
	    return ds;
	}
}
