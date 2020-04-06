package com.springboot.microservice.microservices_item.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Exception500Status extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;

}
