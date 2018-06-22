package br.com.vipmania.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.vipmania.model.Size;
import br.com.vipmania.model.SizeItem;

@Repository
public class SizeItemDAO {

	@PersistenceContext
	private EntityManager manager;
	
	
	public SizeItem get(Long id) {
		return manager.find(SizeItem.class, id);
	}
	
	public List<SizeItem> list(){
		return manager.createQuery("select si from SizeItem si", SizeItem.class).getResultList();
	}
	
	public List<SizeItem> list(Size size){
		return manager.createQuery("select si from SizeItem si where si.size = :size", SizeItem.class).setParameter("size", size).getResultList();
	}

}