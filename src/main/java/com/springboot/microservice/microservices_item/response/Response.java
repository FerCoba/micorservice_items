package com.springboot.microservice.microservices_item.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.microservice.microservices_item.entities.Item;
import com.springboot.microservice.servicec_commons.model.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	private String status;
	private String message;
	private Item item;
	private List<Item> items;
	private Product product;
}
