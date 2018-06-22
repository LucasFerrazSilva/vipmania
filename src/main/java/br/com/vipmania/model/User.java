package br.com.vipmania.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name="TAB_USERS")
public class User implements UserDetails {

	private static final long serialVersionUID = -4639553888379753787L;
	

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	private String email;
	
	private String name;
	
	private String password;
	
	@OneToMany(fetch=EAGER)
	private List<Role> roles;

	@OneToMany(mappedBy="user")
	private List<Address> adresses;
	
	@Transient
	private String actualPassword;
	
	@Transient
	private String newPassword;
	

	public void encryptPassword() {
		password = new BCryptPasswordEncoder().encode(password);
	}
	
	public boolean hasSamePassword(String password) {
		return new BCryptPasswordEncoder().matches(password, this.password);
	}
	
	public boolean isNew() {
		return id == null;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Address> getAdresses() {
		return adresses;
	}

	public void setPasswordAndEncrypt(String newPassword) {
		this.password = new BCryptPasswordEncoder().encode(newPassword);
	}

	public String getActualPassword() {
		return actualPassword;
	}

	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public boolean hasValidNewPassword() {
		return (newPassword != null && !"".equals(newPassword.trim()));
	}
	
}