package br.com.vipmania.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.model.Cart;
import br.com.vipmania.model.CartItem;
import br.com.vipmania.model.PaymentData;
import br.com.vipmania.model.Product;

@Controller
@RequestMapping("/cart")
@Scope(value=SCOPE_REQUEST)
public class CartController {

	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private Cart cart;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping("/add")
	public ModelAndView add(Long id, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/product/list");
		
		Product product = productDao.get(id);
		
		CartItem item = new CartItem(product);
		
		cart.add(item);

		redirectAttributes.addFlashAttribute("message", "Produto adicionado ao carrinho com sucesso");
		
		return modelAndView;
	}
	
	@RequestMapping("")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/cart/list");
		
		modelAndView.addObject("itens", cart.getItens());
		
		return modelAndView;
	}
	
	@RequestMapping(value="/finalize", method=POST)
	public Callable<ModelAndView> finalize(ArrayList<CartItem> itens, RedirectAttributes redirectAttributes) {
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";
			
			String result = "";
			
			try {
				result = restTemplate.postForObject(uri, new PaymentData(cart.getTotalValue()), String.class);
			}
			catch(HttpClientErrorException e) {
				e.printStackTrace();
				result = "Valor maior que o permitido";
			}
			
			redirectAttributes.addFlashAttribute("message", result);
			
			return new ModelAndView("redirect:/product/list");
		};
	}
	
	@RequestMapping("/remove")
	public ModelAndView remove(Long productId, RedirectAttributes redirectAttributes) {
		Product product = productDao.get(productId);
		
		cart.remove(product);
		
		redirectAttributes.addFlashAttribute("message", "Produto removido");
		
		return new ModelAndView("redirect:/cart"); 
	}
	
}