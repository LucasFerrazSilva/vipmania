package br.com.vipmania.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=GET)
	public String loginForm() {
		return "loginForm";
	}
	
}
