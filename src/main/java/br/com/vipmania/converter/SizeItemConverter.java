package br.com.vipmania.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vipmania.dao.SizeItemDAO;
import br.com.vipmania.model.SizeItem;

@Component
public class SizeItemConverter implements Converter<String, SizeItem> {

	@Autowired
	private SizeItemDAO dao;

	
	@Override
	public SizeItem convert(String id) {
		return dao.get(Long.parseLong(id));
	}
	
}