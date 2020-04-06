package com.springboot.microservice.microservices_item.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.microservice.servicec_commons.model.entities.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseRestTemplate {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Product> products;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Product product;
}
