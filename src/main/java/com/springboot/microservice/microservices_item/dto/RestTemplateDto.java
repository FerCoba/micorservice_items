package com.springboot.microservice.microservices_item.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.springboot.microservice.microservices_item.RestTemplateConfig;
import com.springboot.microservice.microservices_item.response.ResponseRestTemplate;
import com.springboot.microservice.servicec_commons.model.entities.Product;

@Component
public class RestTemplateDto {

	@Autowired
	RestTemplateConfig restTemplateConfig;

	public List<ResponseRestTemplate> obtainInformationAllProducts() {

		List<ResponseRestTemplate> productsList = Arrays.asList(restTemplateConfig.clientRestTemplate()
//				.getForObject("http://microservice-product/obtainInformationAllProducts", ResponseRestTemplate.class));
				.getForObject("http://localhost:8090/api/product/obtainInformationAllProducts",
						ResponseRestTemplate.class));
		productsList.removeIf(prod -> prod.getProducts().isEmpty());
		return productsList;
	}

	public ResponseRestTemplate obtainInformationByProductId(Long productId) {
		Map<String, String> param = new HashMap<>();
		param.put("productId", productId.toString());
		ResponseRestTemplate product = restTemplateConfig.clientRestTemplate()
//				.getForObject("http://microservice-product/obtainProductInformation/{productId}", ResponseRestTemplate.class, param);	
				.getForObject("http://localhost:8090/api/product/obtainProductInformation/{productId}",
						ResponseRestTemplate.class, param);
		return product;
	}

	public Product insertNewProduct(Product product) {

		HttpEntity<Product> params = new HttpEntity<>(product);

		ResponseEntity<ResponseRestTemplate> response = restTemplateConfig.clientRestTemplate().exchange(
				"http://localhost:8090/api/product/createNewProduct", HttpMethod.POST, params,
				ResponseRestTemplate.class);
		return response.getBody().getProduct();

	}

	public void deleteProduct(Long productId) {

		Map<String, String> param = new HashMap<>();
		param.put("productId", productId.toString());

		restTemplateConfig.clientRestTemplate().delete("http://localhost:8090/api/product/deleteProduct/{productId}",
				param);

	}

}
