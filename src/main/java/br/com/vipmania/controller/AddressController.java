package br.com.vipmania.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.vipmania.dao.AddressDAO;
import br.com.vipmania.model.Address;
import br.com.vipmania.model.User;
import br.com.vipmania.validation.AddressValidator;

@Controller
public class AddressController {

	@Autowired
	private AddressDAO dao;
	
	
	@RequestMapping("/address/form/{id}")
	public ModelAndView form(@PathVariable("id") Address address, @AuthenticationPrincipal User user) {
		ModelAndView modelAndView = new ModelAndView("/address/form");
		
		modelAndView.addObject("address", (address == null ? new Address(user) : address));
		
		return modelAndView;
	}
	
	@RequestMapping("/address/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Address address) {
		ModelAndView modelAndView = new ModelAndView("/address/detail");
		
		modelAndView.addObject("address", address);
		
		return modelAndView;
	}

	@RequestMapping("/address/list-all")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/address/list");
		
		modelAndView.addObject("adresses", dao.list());
		
		return modelAndView;
	}

	@RequestMapping("/address/list")
	public ModelAndView list(@AuthenticationPrincipal User user) {
		ModelAndView modelAndView = new ModelAndView("/address/list");
		
		modelAndView.addObject("adresses", dao.list(user));
		
		return modelAndView;
	}
	
	@RequestMapping(value="/address/save", method=POST)
	public ModelAndView save(@Valid Address address, BindingResult bindingResult, @AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return form(address, user);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/address/list");
		
		dao.save(address);
		
		redirectAttributes.addFlashAttribute("message", "Endereço cadastrado com sucesso!");
		
		return modelAndView;
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new AddressValidator());
	}
	
}