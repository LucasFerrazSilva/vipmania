package br.com.vipmania.controller;

import static com.mysql.jdbc.StringUtils.isEmptyOrWhitespaceOnly;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.vipmania.dao.CategoryDAO;
import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.dao.SizeDAO;
import br.com.vipmania.model.Category;
import br.com.vipmania.model.Product;
import br.com.vipmania.model.Size;
import br.com.vipmania.service.FileService;
import br.com.vipmania.validation.ProductValidation;

@Controller
public class ProductController {

	@Autowired
	private ProductDAO dao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private SizeDAO sizeDao;
	
	@Autowired
	private FileService fileService;
	
	
	@RequestMapping("/product/form/{id}")
	public ModelAndView form(@PathVariable("id") Product product) {
		ModelAndView modelAndView = new ModelAndView("/product/form");
		
		List<Size> sizes = sizeDao.list();
		
		modelAndView.addObject("product", (product != null ? product : new Product()));
		modelAndView.addObject("categories", categoryDao.list());
		modelAndView.addObject("sizes", sizes);
		modelAndView.addObject("sizeItems", (product != null && product.getSize() != null ? product.getSize().getOrderedItens() : sizes.get(0).getOrderedItens()));
		
		return modelAndView;
	}
	
	@RequestMapping("/product/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("/product/detail");
		
		Product product = dao.get(id);
		
		modelAndView.addObject("product", product);
		modelAndView.addObject("relatedProducts", dao.listRelated(product));
		
		return modelAndView;
	}
	
	@RequestMapping("/product/save")
	@Transactional
	public ModelAndView save(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes, MultipartFile photo ) {
		product.updateProductAtQuantities();
		
		dao.save(product);
		
		if(photo != null && !isEmptyOrWhitespaceOnly(photo.getOriginalFilename())) {
			String photoPath = fileService.write("photos/" + product.getId(), photo);
		
			product.setPhotoPath(photoPath);
			
			dao.save(product);
		}
		
		redirectAttributes.addFlashAttribute("message", "Produto cadastrado com sucesso");
		
		return new ModelAndView("redirect:/product/list");
	}
	
	@RequestMapping("/product/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/product/list");
		
		modelAndView.addObject("products", dao.list());
		
		return modelAndView;
	}

	@RequestMapping("/product/list/{category}")
	public ModelAndView list(@PathVariable("category") String categoryName, Long page) {
		Category category = categoryDao.get(categoryName);
		
		ModelAndView modelAndView = buildList(page, dao.getPageCount(category), dao.listValidsByCategoryPaginated(category, page));
		
		modelAndView.addObject("category", category.getName());
		
		return modelAndView;
	}
	
	@RequestMapping("/product/search")
	public ModelAndView search(String word, Long page) {
		ModelAndView modelAndView = buildList(page, dao.getPageCount(word), dao.search(word, page));
		
		modelAndView.addObject("word", word);
		
		return modelAndView;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProductValidation());
	}
	
	public ModelAndView buildList(Long page, int pageCount, List<Product> products) {
		page = (page == null ? 1L : page);
		
		ModelAndView modelAndView = new ModelAndView("/product/listForClient");
		
		int[] pages = new int[pageCount];
		
		for(int i = 1 ; i <= pageCount ; i++) {
			pages[i-1] = i;
		}
		
		modelAndView.addObject("products", products);
		modelAndView.addObject("pages", pages);
		modelAndView.addObject("actualPage", page);
		modelAndView.addObject("finalPage", pageCount);
		
		return modelAndView;
	}
	
}