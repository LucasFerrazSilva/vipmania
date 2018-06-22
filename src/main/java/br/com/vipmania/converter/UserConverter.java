package br.com.vipmania.converter;

import static java.lang.Long.parseLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vipmania.dao.UserDAO;
import br.com.vipmania.model.User;

@Component
public class UserConverter implements Converter<String, User>{

	@Autowired
	private UserDAO dao;
	
	
	@Override
	public User convert(String id) {
		return dao.getWithoutCheck(parseLong(id));
	}

}