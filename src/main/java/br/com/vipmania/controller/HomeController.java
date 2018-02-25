package br.com.vipmania.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.dao.UserDAO;

@Controller
public class HomeController {

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("/")
	@Cacheable(value="productsHome")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		modelAndView.addObject("products", dao.list());
		
		return modelAndView;
	
	}
	
	@ResponseBody
	@RequestMapping("/qw56d1qw89d4qwd56as4d89qwd156asd48a97qw9e8sd65asw81a6s54q8w9")
	@Transactional
	public String createAdmin() {
		userDao.createAdmin();
		
		return "Admin created";
	}
	
}
