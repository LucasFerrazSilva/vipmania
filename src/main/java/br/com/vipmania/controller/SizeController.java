package br.com.vipmania.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.vipmania.dao.SizeDAO;
import br.com.vipmania.model.Size;
import br.com.vipmania.wrapper.SizeItemWrapper;

@Controller
public class SizeController {
	
	@Autowired
	private SizeDAO dao;

	
	@RequestMapping("/size/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/size/list");
		
		modelAndView.addObject("sizes", dao.list());
		
		return modelAndView;
	}
	
	@RequestMapping("/size/form/{id}")
	public ModelAndView form(@PathVariable("id") Size size) {
		ModelAndView modelAndView = new ModelAndView("/size/form");
		
		modelAndView.addObject("size", size);
		modelAndView.addObject("items", size.getOrderedItens());
		
		return modelAndView;
	}
	
	@RequestMapping("/size/save")
	@Transactional
	public ModelAndView save(Size size) {
		ModelAndView modelAndView = new ModelAndView("redirect:/size/list");
		
		dao.save(size);
		
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/size/list-items/{id}")
	public List<SizeItemWrapper> getObject(@PathVariable("id") Size size) {
		return size.getItemsWrappers();
	}

}