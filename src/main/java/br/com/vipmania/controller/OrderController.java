package br.com.vipmania.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.vipmania.dao.OrderDAO;
import br.com.vipmania.model.Order;
import br.com.vipmania.model.User;

@Controller
public class OrderController {
	
	@Autowired
	private OrderDAO orderDao;
	

	@RequestMapping("/order/list-all")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/order/list");
		
		modelAndView.addObject("orders", orderDao.list());
		
		return modelAndView;
	}

	@RequestMapping("/order/list")
	public ModelAndView list(@AuthenticationPrincipal User user) {
		ModelAndView modelAndView = new ModelAndView("/order/list");
		
		modelAndView.addObject("orders", orderDao.list(user));
		
		return modelAndView;
	}
	
	@RequestMapping("/order/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Order order) {
		ModelAndView modelAndView = new ModelAndView("/order/detail");
		
		modelAndView.addObject("order", order);
		
		return modelAndView;
	}
	
}
