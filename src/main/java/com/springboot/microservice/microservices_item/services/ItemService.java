package com.springboot.microservice.microservices_item.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.microservice.microservices_item.dto.RestTemplateDto;
import com.springboot.microservice.microservices_item.entities.Item;
import com.springboot.microservice.microservices_item.exceptions.Exception500Status;
import com.springboot.microservice.microservices_item.response.ResponseRestTemplate;
import com.springboot.microservice.servicec_commons.model.entities.Product;

@Service
public class ItemService {

	@Autowired
	RestTemplateDto restTemplateDto;
	
	@HystrixCommand(defaultFallback = "exceptionMethod")
	public List<Item> obtainInformationAllItems() {
		List<ResponseRestTemplate> products = restTemplateDto.obtainInformationAllProducts();
		List<Product> productsList = products.listIterator().next().getProducts();

		for (Product product : productsList) {
			Product prod = new Product();
			prod.setId(product.getId());
			prod.setCrationDate(product.getCrationDate());
			prod.setPrice(product.getPrice());
			prod.setProductName(product.getProductName());
		}
		return productsList.stream().map(pr -> new Item(pr, 0)).collect(Collectors.toList());
	}
	//en el caso de que el servicio microservice_product retorne un error el siguiente metodo debe de ejecutarse
	public List<Item> exceptionMethod() throws Exception500Status {
		throw new Exception500Status();
	}
	
	public Item obtainInformationItemById(Long productId, Integer quantity) {
		ResponseRestTemplate product = restTemplateDto.obtainInformationByProductId(productId);
		return new Item(product.getProduct(), quantity);
	}
	
	public Product insertNewProduct(Product product) {
		return restTemplateDto.insertNewProduct(product);
	}
	
	public void deleteProduct(Long productId ) {
		restTemplateDto.deleteProduct(productId);
	}

}
