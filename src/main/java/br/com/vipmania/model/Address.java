package br.com.vipmania.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TAB_ADRESSES")
public class Address {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@Column(name="RECEIVER")
	private String receiver;
	
	@Column(name="CEP")
	private String cep;
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="NUMBER")
	private String number;
	
	@Column(name="COMPLEMENT")
	private String complement;
	
	@Column(name="REFERENCE")
	private String reference;
	
	@Column(name="DISTRICT")
	private String district;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;

	
	public Address() {
		//noop
	}

	public Address(User user) {
		this.user = user;
	}


	public String getFormattedAddress() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getStreet()).append(", ")
			   .append(getNumber()).append(" - ")
			   .append(getDistrict()).append(" - ")
			   .append(getCity()).append(", ")
			   .append(getState()).append(" ")
			   .append(getCep());
		
		return builder.toString();
	}
	
	public boolean isNew() {
		return id == null;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	
	public Long getUserId() {
		return (user == null ? 0 : user.getId());
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUserIfNull(User user) {
		this.user = (this.user == null ? user : this.user);		
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


}