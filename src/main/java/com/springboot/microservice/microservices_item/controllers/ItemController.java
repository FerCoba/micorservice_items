package com.springboot.microservice.microservices_item.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservice.microservices_item.entities.Item;
import com.springboot.microservice.microservices_item.exceptions.Exception500Status;
import com.springboot.microservice.microservices_item.response.Response;
import com.springboot.microservice.microservices_item.services.ItemService;
import com.springboot.microservice.servicec_commons.model.entities.Product;

@RestController
@RequestMapping(produces = "application/json")
public class ItemController {

	@Autowired
	ItemService itemSercice;

	@GetMapping("/obtainItemsInformation")
	public ResponseEntity<Response> obtainItemsInformation() throws Exception {
		List<Item> itemsList = new ArrayList<>();
		try {
			itemsList = itemSercice.obtainInformationAllItems();
		} catch (Exception e) {
			throw new Exception500Status();
		}

		return itemsList.isEmpty() ? new ResponseEntity<>(
				new Response(String.valueOf(HttpStatus.OK.value()), "Not data Found", null, null, null), HttpStatus.OK)
				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK.value()), null, null, itemsList, null),
						HttpStatus.OK);
	}

	@GetMapping("id/{id}/quantity/{quantity}/obtainInformationItemById")
	public ResponseEntity<Response> obtainInformationItemById(@PathVariable Long id, @PathVariable Integer quantity) {

		Item item = itemSercice.obtainInformationItemById(id, quantity);

		return item == null ? new ResponseEntity<>(
				new Response(String.valueOf(HttpStatus.OK.value()), "Not data Found", null, null, null), HttpStatus.OK)
				: new ResponseEntity<>(new Response(String.valueOf(HttpStatus.OK.value()), null, item, null, null),
						HttpStatus.OK);
	}

	@PostMapping("/insertNewProduct")
	public ResponseEntity<Response> insertNewProduct(@RequestBody Product parameters) {

		Product product = itemSercice.insertNewProduct(parameters);

		return product == null ? new ResponseEntity<>(
				new Response(String.valueOf(HttpStatus.OK.value()), "Not data Found", null, null, null), HttpStatus.OK)
				: new ResponseEntity<>(
						new Response(String.valueOf(HttpStatus.CREATED.value()), null, null, null, product),
						HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<Response> insertNewProduct(@PathVariable("productId") Long productId) {

		itemSercice.deleteProduct(productId);

		return new ResponseEntity<>(new Response(String.valueOf(HttpStatus.NO_CONTENT.value()),
				"product deleted successfully.", null, null, null), HttpStatus.NO_CONTENT);
	}

}
