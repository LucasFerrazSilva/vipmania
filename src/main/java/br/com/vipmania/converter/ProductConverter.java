package br.com.vipmania.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vipmania.dao.ProductDAO;
import br.com.vipmania.model.Product;

@Component
public class ProductConverter implements Converter<String, Product>{

	@Autowired
	private ProductDAO dao;
	
	
	@Override
	public Product convert(String id) {
		return dao.get(Long.parseLong(id));
	}

}