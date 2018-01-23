package br.com.vipmania.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.vipmania.dao.ProductDAO;

@Controller
public class HomeController {

	@Autowired
	private ProductDAO dao;
	
	@RequestMapping("/")
	@Cacheable(value="productsHome")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		modelAndView.addObject("products", dao.list());
		
		return modelAndView;
	
	}
	
}
