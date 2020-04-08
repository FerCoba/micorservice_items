package com.springboot.microservice.microservices_item.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);
	private static final String ENTRY_METHOD_MESSAGE = "entry in method {}.";
	private static final String GET_METHOD_MESSAGE = "get method {} {}.";
	

	@Autowired
	RestTemplateDto restTemplateDto;

	@HystrixCommand(defaultFallback = "exceptionMethodObtainInformationAllItems")
	public List<Item> obtainInformationAllItems() {

		LOGGER.info(ENTRY_METHOD_MESSAGE, "obtainInformationAllItems");

		LOGGER.info(GET_METHOD_MESSAGE, "restTemplateDto.obtainInformationAllProducts()", "for get info.");
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

	// en el caso de que el servicio microservice_product retorne un error el
	// siguiente metodo debe de ejecutarse
	public List<Item> exceptionMethodObtainInformationAllItems() throws Exception500Status {
		throw new Exception500Status();
	}

	public Item obtainInformationItemById(Long productId, Integer quantity) {
		LOGGER.info(ENTRY_METHOD_MESSAGE, "obtainInformationItemById");

		LOGGER.info(GET_METHOD_MESSAGE, "restTemplateDto.obtainInformationByProductId(productId)", "for get info.");
		ResponseRestTemplate product = restTemplateDto.obtainInformationByProductId(productId);
		return new Item(product.getProduct(), quantity);
	}

	public Product createNewProduct(Product product) {
		LOGGER.info(ENTRY_METHOD_MESSAGE, "insertNewProduct");
		LOGGER.info(GET_METHOD_MESSAGE, "restTemplateDto.createNewProduct(product)", "for get info.");
		return restTemplateDto.createNewProduct(product);
	}

	public void deleteProduct(Long productId) {
		LOGGER.info(ENTRY_METHOD_MESSAGE, "deleteProduct");

		LOGGER.info(GET_METHOD_MESSAGE, "restTemplateDto.deleteProduct(productId)", "for get info.");
		restTemplateDto.deleteProduct(productId);
	}

}
