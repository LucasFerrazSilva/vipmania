package br.com.vipmania.dao;

import static java.util.Arrays.asList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.vipmania.model.Role;
import br.com.vipmania.model.User;

@Repository
public class UserDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public User get(Long id) {
		User user = entityManager.find(User.class, id);
		
		if(user == null)
			throw new UsernameNotFoundException("User with id " + id + " not found");
		
		return user;
	}
	
	
	public User getWithoutCheck(Long id) {
		return entityManager.find(User.class, id);
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		TypedQuery<User> query = entityManager.createQuery("select u from User u where u.email = :email", User.class);
		
		query.setParameter("email", email);
		
		return (UserDetails) query.getSingleResult();
	}

	public void createAdmin() {
		Role role = entityManager.find(Role.class, "ROLE_ADMIN");
		
//		if(role == null) {
//			role = new Role("ROLE_ADMIN");
//			entityManager.persist(role);
//		}
//		
		User admin = new User();
		
		admin.setName("Admin6");
		admin.setEmail("admin6@vipmania.com");
		admin.setPassword("$2a$04$qP517gz1KNVEJUTCkUQCY.JzEoXzHFjLAhPQjrg5iP6Z/UmWjvUhq");
		admin.setRoles(asList(role));

		entityManager.persist(admin);
	}


	public List<User> list() {
		return entityManager.createQuery("select u from User u order by u.name", User.class).getResultList();
	}


	public void save(User user) {
		if(user == null)
			throw new IllegalArgumentException("User cannot be null");
		
		if(user.isNew()) {
			user.encryptPassword();
			entityManager.persist(user);
		}
		else {
			entityManager.merge(user);
		}
	}
	
	
	
}
