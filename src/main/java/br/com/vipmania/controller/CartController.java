package br.com.vipmania.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.vipmania.dao.AddressDAO;
import br.com.vipmania.dao.OrderDAO;
import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.model.Address;
import br.com.vipmania.model.Cart;
import br.com.vipmania.model.CartItem;
import br.com.vipmania.model.CartItemList;
import br.com.vipmania.model.Order;
import br.com.vipmania.model.User;
import br.com.vipmania.service.EmailService;
import br.com.vipmania.service.FreteService;
import br.com.vipmania.wrapper.CorreiosServicoWrapper;

@Controller
@RequestMapping("/cart")
@Scope(value=SCOPE_REQUEST)
public class CartController {

	@Autowired
	private Cart cart;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private AddressDAO addressDao;
	
	@Autowired
	private FreteService freteService;
	
	
	@RequestMapping("/add/")
	public ModelAndView add(CartItemList items, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		
		cart.addAll(items);

		redirectAttributes.addFlashAttribute("message", "Produto adicionado ao carrinho com sucesso");
		
		return modelAndView;
	}
	
	@RequestMapping("")
	public ModelAndView list(@AuthenticationPrincipal User user) {
		ModelAndView modelAndView = new ModelAndView("/cart/list");
		
		List<Address> addresses = addressDao.list(user);
		CorreiosServicoWrapper frete = (addresses != null && addresses.size() > 0 ? freteService.getServicoWrapper(addresses.get(0).getCep()) : null);
		
		modelAndView.addObject("itens", cart.getItens());
		modelAndView.addObject("frete", frete);
		modelAndView.addObject("adresses", addresses);
		
		return modelAndView;
	}
	
	@RequestMapping("/send-to-paypal")
	public ModelAndView sendToPaypal() {
		return new ModelAndView("/cart/finalize");
	}
	
	@RequestMapping(value="/finalize", method=POST)
	@Transactional
	public ModelAndView finalize(@AuthenticationPrincipal User user, CartItemList items, Address address, RedirectAttributes redirectAttributes) {
		cart.setItens(items.getItens(productDao));

		if(cart.hasInvalidQuantity()) {
			redirectAttributes.addFlashAttribute("message", "A quantidade selecionada de um ou mais produtos é maior do que a quantidade em estoque");
			
			return new ModelAndView("redirect:/cart");
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/cart/send-to-paypal");
		
		CorreiosServicoWrapper frete = freteService.getServicoWrapper(address.getCep());
		
		redirectAttributes.addFlashAttribute("items", cart.getItens());
		redirectAttributes.addFlashAttribute("frete", frete);
		
		Order order = cart.generateOrder(user, address, frete);
		
		orderDao.save(order);
		
		emailService.send(user.getEmail(), order.getId());
		
		cart.clearItens();
		
		return modelAndView;
	}
	
	@RequestMapping("/finalize-test")
	public void finalizeTest() {
		emailService.send("lucasferrazsilva@hotmail.com", 1L);
	}
	
	@RequestMapping("/remove")
	public ModelAndView remove(CartItem item, RedirectAttributes redirectAttributes) {
		cart.remove(item);
		
		redirectAttributes.addFlashAttribute("message", "Produto removido");
		
		return new ModelAndView("redirect:/cart"); 
	}
	
}