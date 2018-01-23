package br.com.vipmania.controller;

import static com.mysql.jdbc.StringUtils.isEmptyOrWhitespaceOnly;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.model.Product;
import br.com.vipmania.service.FileService;
import br.com.vipmania.validation.ProductValidation;

@Controller
public class ProductController {

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/product/form")
	public ModelAndView form(Product product) {
		ModelAndView modelAndView = new ModelAndView("/product/form");
		
		//modelAndView.addObject("name", valores);
		
		return modelAndView;
	}
	
	@RequestMapping("/product/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("/product/detail");
		
		modelAndView.addObject("product", dao.get(id));
		
		return modelAndView;
	}
	
	@RequestMapping("/product/save")
	@CacheEvict(value="productsHome", allEntries=true)
	public ModelAndView save(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes, MultipartFile photo ) {
		if(bindingResult.hasErrors())
			return form(product);
		
		if(photo != null && !isEmptyOrWhitespaceOnly(photo.getOriginalFilename())) {
			String photoPath = fileService.write("photos", photo);
		
			product.setPhotoPath(photoPath);
		}
		
		dao.save(product);
		
		redirectAttributes.addFlashAttribute("message", "Produto cadastrado com sucesso");
		
		return new ModelAndView("redirect:/product/list");
	}
	
	@RequestMapping("/product/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/product/list");
		
		modelAndView.addObject("products", dao.list());
		
		return modelAndView;
	}

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProductValidation());
	}
	
}