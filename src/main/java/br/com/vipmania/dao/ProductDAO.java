package br.com.vipmania.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipmania.model.Product;

@Repository
@Transactional
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void save(Product product) {
		manager.persist(product);
	}


	public List<Product> list() {
		return manager.createQuery("select p from Product p", Product.class).getResultList();
	}


	public Product get(Long id) {
		return manager.find(Product.class, id);
	}
	
}