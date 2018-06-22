package br.com.vipmania.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.vipmania.dao.CategoryDAO;
import br.com.vipmania.model.Category;

@Controller
public class CategoryController {

	@Autowired
	private CategoryDAO dao;
	
	@RequestMapping("/category/form/{id}")
	public ModelAndView form(@PathVariable("id") Category category) {
		ModelAndView modelAndView = new ModelAndView("/category/form");
		
		modelAndView.addObject("category", category);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/category/save", method=POST)
	public ModelAndView save(Category category) {
		ModelAndView modelAndView = new ModelAndView("redirect:/category/list");
		
		dao.save(category);
		
		return modelAndView;
	}

	@RequestMapping("/category/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/category/list");
		
		modelAndView.addObject("categories", dao.list());
		
		return modelAndView;
	}
	
}