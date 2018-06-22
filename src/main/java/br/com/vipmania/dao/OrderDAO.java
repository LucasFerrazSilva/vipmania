package br.com.vipmania.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.vipmania.model.Order;
import br.com.vipmania.model.OrderItem;
import br.com.vipmania.model.User;

@Repository
public class OrderDAO {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private QuantityBySizeDAO quantityBySizeDAO;
	
	
	public void save(Order order) {
		manager.persist(order);
		
		for(OrderItem item : order.getItens()) {
			manager.persist(item);
			
			quantityBySizeDAO.reduce(item);
		}
	}


	public List<Order> list(User user) {
		return manager.createQuery("select o from Order o where o.user = :user order by o.date desc", Order.class).setParameter("user", user).getResultList();
	}


	public List<Order> list() {
		return manager.createQuery("select o from Order o order by o.id", Order.class).getResultList();
	}


	public Order get(Long id) {
		return manager.find(Order.class, id);
	}
	
}