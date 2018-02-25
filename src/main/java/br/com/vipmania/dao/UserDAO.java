package br.com.vipmania.dao;

import static java.util.Arrays.asList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	
	public User get(String email) {
		List<User> list = entityManager.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email).getResultList();
		
		if(list == null || list.isEmpty())
			throw new UsernameNotFoundException("User with email " + email + " not found");
		
		return list.get(0);
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return get(email);
	}

	public void createAdmin() {
		User admin = new User();
		
		admin.setName("Admin2");
		admin.setEmail("admin2@vipmania.com");
		admin.setPassword("$2y$10$A73kdpuBpTtXem4yd98xAu0PKPRWIsceXNPgHylrZhHRIxbLcYNUq");
		admin.setRoles(asList(new Role("ROLE_ADMIN")));

		entityManager.persist(admin);
	}
	
}
