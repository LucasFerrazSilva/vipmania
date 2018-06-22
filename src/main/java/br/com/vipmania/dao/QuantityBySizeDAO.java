package br.com.vipmania.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.vipmania.model.OrderItem;
import br.com.vipmania.model.Product;
import br.com.vipmania.model.QuantityBySize;

@Repository
public class QuantityBySizeDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public QuantityBySize get(Long id) {
		return manager.find(QuantityBySize.class, id);
	}
	
	public void clear(Product product) {
		manager.createQuery("delete from QuantityBySize qbs where qbs.product = :product").setParameter("product", product).executeUpdate();
	}
	

	public void reduce(OrderItem item) {
		reduce(item.getQuantityBySize(), item.getQuantity());
	}

	private void reduce(QuantityBySize quantityBySize, Long quantity) {
		quantityBySize.reduce(quantity);
		
		manager.merge(quantityBySize);
	}
	
}
