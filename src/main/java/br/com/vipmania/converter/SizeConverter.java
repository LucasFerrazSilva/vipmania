package br.com.vipmania.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vipmania.dao.SizeDAO;
import br.com.vipmania.model.Size;

@Component
public class SizeConverter implements Converter<String, Size> {

	@Autowired
	private SizeDAO dao;
	
	@Override
	public Size convert(String id) {
		return dao.get(Long.parseLong(id));
	}

}
