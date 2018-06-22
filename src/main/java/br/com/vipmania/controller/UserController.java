package br.com.vipmania.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.vipmania.dao.UserDAO;
import br.com.vipmania.model.User;
import br.com.vipmania.validation.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserDAO dao;
	
	
	@RequestMapping("/user/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/user/list");
		
		modelAndView.addObject("users", dao.list());
		
		return modelAndView;
	}
	
	@RequestMapping("/user/form/{id}")
	public ModelAndView form(@PathVariable("id") User user) {
		ModelAndView modelAndView = new ModelAndView("/user/form");
		
		modelAndView.addObject("user", (user != null ? user : new User()));
		
		return modelAndView;
	}
	
	@RequestMapping("/user/home")
	public ModelAndView home(@AuthenticationPrincipal User user) {
		ModelAndView modelAndView = new ModelAndView("/user/form");
		
		modelAndView.addObject("user", (user != null ? user : new User()));
		
		return modelAndView;
	}
	
	@RequestMapping(value="/user/save", method=POST)
	@Transactional
	public ModelAndView save(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return form(user);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		
		dao.save(user);
		
		redirectAttributes.addFlashAttribute("message", "Usuário cadastrado com sucesso!");
		
		return modelAndView;
	}

	@RequestMapping("/user/changePasswordForm/{id}")
	public ModelAndView changePasswordForm(@PathVariable("id") User user) {
		ModelAndView modelAndView = new ModelAndView("/user/changePasswordForm");
		
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/user/changePassword", method=POST)
	@Transactional
	public ModelAndView changePassword(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		User actualUser = dao.get(user.getId());
		
		if(actualUser.hasSamePassword(user.getActualPassword()) && user.hasValidNewPassword()) {
			actualUser.setPasswordAndEncrypt(user.getNewPassword());
			
			ModelAndView modelAndView = new ModelAndView("redirect:/");
			
			dao.save(actualUser);
			
			redirectAttributes.addFlashAttribute("message", "Senha alterada com sucesso!");
			
			return modelAndView;	
		}
		else {
			ModelAndView modelAndView = new ModelAndView("redirect:/user/changePasswordForm/" + user.getId());
			
			redirectAttributes.addFlashAttribute("message", "Erro ao alterar senha");
			
			return modelAndView;
		}
		
	}
	
	@RequestMapping("/user/createAccount")
	public ModelAndView createAccount() {
		ModelAndView modelAndView = new ModelAndView("/user/createAccount");
		
		modelAndView.addObject("user", new User());
		
		return modelAndView;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UserValidator());
	}
	
}