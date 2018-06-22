package br.com.vipmania.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vipmania.dao.AddressDAO;
import br.com.vipmania.model.Address;

@Component
public class AddressConverter implements Converter<String, Address>{

	@Autowired
	private AddressDAO dao;
	
	
	@Override
	public Address convert(String id) {
		return dao.get(Long.parseLong(id));
	}

}