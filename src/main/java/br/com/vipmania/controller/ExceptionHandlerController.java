package br.com.vipmania.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	public String validateNotFoundProduct(Exception e) {
		e.printStackTrace();
		
		return "error";
	}
	
}
