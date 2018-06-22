package br.com.vipmania.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vipmania.dao.CategoryDAO;
import br.com.vipmania.model.Category;

@Component
public class CategoryConverter implements Converter<String, Category>{

	@Autowired
	private CategoryDAO dao;
	
	
	@Override
	public Category convert(String id) {
		return dao.get(Long.parseLong(id));
	}

}