package br.com.vipmania.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="TAB_ROLES")
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1913706246907590489L;
	
	@Id
	private String name;

	
	public Role() {
		//noop
	}
	
	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return getName();
	}
	
}