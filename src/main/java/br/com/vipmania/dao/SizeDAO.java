package br.com.vipmania.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.vipmania.model.Size;

@Repository
public class SizeDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public Size get(Long id) {
		return manager.find(Size.class, id);
	}
	
	public List<Size> list(){
		return manager.createQuery("select s from Size s order by s.name", Size.class).getResultList();
	}

	public void save(Size size) {
		size.removeEmptyItens();
		
		if(size.isNew()) {
			manager.persist(size);
		}
		else {
			manager.merge(size);
		}
		
	}
	
}