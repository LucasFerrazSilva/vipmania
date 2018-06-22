package br.com.vipmania.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.vipmania.model.Address;
import br.com.vipmania.model.User;

@Repository
@Transactional
public class AddressDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public Address get(Long id) {
		return manager.find(Address.class, id); 
	}
	
	public List<Address> list(){
		return manager.createQuery("select a from Address a order by a.street", Address.class).getResultList();
	}
	
	public List<Address> list(User user){
		return manager.createQuery("select a from Address a where a.user = :user order by a.street", Address.class).setParameter("user", user).getResultList();
	}
	
	public void save(Address address) {
		if(address.isNew()) {
			manager.persist(address);
		}
		else {
			manager.merge(address);
		}
	}
	
}