package br.com.vipmania.dao;

import java.util.List;

import br.com.vipmania.model.Product;

public class ProductDAOTest {

	public void deveSomarTodosOsPrecosPelaCategoria() {
		ProductDAO dao = new ProductDAO();
		
		List<Product> calças = dao.listByCategory("");
	}

}