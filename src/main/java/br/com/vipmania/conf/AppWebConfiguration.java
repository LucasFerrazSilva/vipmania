package br.com.vipmania.conf;

import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.vipmania.controller.HomeController;
import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.model.Cart;
import br.com.vipmania.service.FileService;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProductDAO.class, FileService.class, Cart.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		resolver.setExposedContextBeanNames("cart");
		
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		
		messageSource.setBasename("/WEB-INF/message");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		
		return messageSource;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		
		formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatterRegistrar.registerFormatters(conversionService);
		
		return conversionService;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, MINUTES);
		
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		
		cacheManager.setCacheBuilder(builder);
		
		return cacheManager;
	}
	
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> viewResolvers = new ArrayList<>();
		
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonViewResolver());
		
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		
		viewResolver.setViewResolvers(viewResolvers);
		viewResolver.setContentNegotiationManager(manager);
		
		return viewResolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
}