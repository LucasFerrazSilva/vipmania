package br.com.vipmania.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.vipmania.controller.HomeController;
import br.com.vipmania.converter.AddressConverter;
import br.com.vipmania.converter.CategoryConverter;
import br.com.vipmania.converter.OrderConverter;
import br.com.vipmania.converter.ProductConverter;
import br.com.vipmania.converter.QuantityBySizeConverter;
import br.com.vipmania.converter.SizeConverter;
import br.com.vipmania.converter.SizeItemConverter;
import br.com.vipmania.converter.UserConverter;
import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.model.Cart;
import br.com.vipmania.service.FileService;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProductDAO.class, FileService.class, Cart.class, CategoryConverter.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private CategoryConverter categoryConverter;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private OrderConverter orderConverter;
	
	@Autowired
	private AddressConverter addressConverter;
	
	@Autowired
	private SizeConverter sizeConverter;
	
	@Autowired
	private SizeItemConverter sizeItemConverter;
	
	@Autowired
	private QuantityBySizeConverter quantityBySizeConverter;
	
	
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
		addFormatters(conversionService);
		
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

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setUsername("comprasvipmania@gmail.com");
		mailSender.setPassword("lucas123612");
		mailSender.setPort(587);
		
		Properties mailProperties = new Properties();
		
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);

		mailSender.setJavaMailProperties(mailProperties);
		
		return mailSender;
	}

	@Override
    public void addFormatters(FormatterRegistry formatterRegistry){
        formatterRegistry.addConverter(categoryConverter);
        formatterRegistry.addConverter(productConverter);
        formatterRegistry.addConverter(userConverter);
        formatterRegistry.addConverter(orderConverter);
        formatterRegistry.addConverter(addressConverter);
        formatterRegistry.addConverter(sizeConverter);
        formatterRegistry.addConverter(sizeItemConverter);
        formatterRegistry.addConverter(quantityBySizeConverter);
    }
	
}