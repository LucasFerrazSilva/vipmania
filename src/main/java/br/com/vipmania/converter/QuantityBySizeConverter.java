package br.com.vipmania.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vipmania.dao.QuantityBySizeDAO;
import br.com.vipmania.model.QuantityBySize;

@Component
public class QuantityBySizeConverter implements Converter<String, QuantityBySize>{

	@Autowired
	private QuantityBySizeDAO dao;
	
	
	@Override
	public QuantityBySize convert(String id) {
		return dao.get(Long.parseLong(id));
		}

}
