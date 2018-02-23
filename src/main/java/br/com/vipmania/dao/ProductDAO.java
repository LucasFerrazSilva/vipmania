package br.com.vipmania.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		Product product = manager.find(Product.class, id);
		
		if(product == null)
			throw new NoResultException("No result found for product with id " + id);
		
		return product;
	}

	public List<Product> listByCategory(String category) {
		TypedQuery<Product> query = manager.createQuery("select p from Product p where p.category = :category", Product.class).setParameter("category", category);
		
		return query.getResultList();
	}

	public BigDecimal sumProductValueByCategory(String category) {
		TypedQuery<BigDecimal> query = manager.createQuery("select sum(p.value) from Product p where p.category = :category", BigDecimal.class).setParameter("category", category);
		
		return query.getSingleResult();
	}
	
}