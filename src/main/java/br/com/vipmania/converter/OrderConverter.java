package br.com.vipmania.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.vipmania.dao.OrderDAO;
import br.com.vipmania.model.Order;

@Component
public class OrderConverter implements Converter<String, Order>{

	@Autowired
	private OrderDAO dao;
	
	
	@Override
	public Order convert(String id) {
		return dao.get(Long.parseLong(id));
	}

}