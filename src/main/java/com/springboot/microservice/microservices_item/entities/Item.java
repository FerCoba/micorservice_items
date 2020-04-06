package com.springboot.microservice.microservices_item.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.microservice.servicec_commons.model.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private Product product;
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private Integer quantity;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	public Double getTotal() {
		return product.getPrice().doubleValue() * this.getQuantity().doubleValue();
	}
	
}
