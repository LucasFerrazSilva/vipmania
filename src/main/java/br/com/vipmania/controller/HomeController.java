package br.com.vipmania.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.vipmania.dao.ProductDAO;

@Controller
public class HomeController {

	@Autowired
	private ProductDAO dao;
	
	
	@RequestMapping("/")
	public ModelAndView index() throws IOException {
		ModelAndView modelAndView = new ModelAndView("index");
		
		modelAndView.addObject("products", dao.listMostRecents());
		
		return modelAndView;
	
	}
	
}
