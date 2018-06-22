package br.com.vipmania.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.vipmania.model.Category;

@Repository
@Transactional
public class CategoryDAO {

	@PersistenceContext
	private EntityManager manager;

	
	public Category get(Long id) {
		return manager.find(Category.class, id);
	}


	public List<Category> list() {
		return manager.createQuery("select c from Category c order by c.name", Category.class).getResultList();
	}
	
	public void save(Category category) {
		if(category.isNew()) {
			manager.persist(category);
		}
		else {
			manager.merge(category);
		}
		
	}


	public Category get(String categoryName) {
		try {
			TypedQuery<Category> query = manager.createQuery("select c from Category c where c.name = ?", Category.class);
		
			query.setParameter(1, categoryName);
			
			return query.getSingleResult();
		}
		catch(Exception e) {
			System.out.println("Erro ao procurar categoria " + categoryName + "\n" + e);
			return null;
		}
	}
	
}