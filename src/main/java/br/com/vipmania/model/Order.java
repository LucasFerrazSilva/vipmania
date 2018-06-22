package br.com.vipmania.model;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.vipmania.wrapper.CorreiosServicoWrapper;

@Entity
@Table(name="TAB_ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Address address;
	
	@OneToMany(mappedBy="order")	
	private List<OrderItem> itens;
	
	@Column(name="DATE")
	private Calendar date;
	
	private BigDecimal freteValue;
	
	private String fretePrazo;

	
	public Order() {
		//noop
	}
	
	public Order(List<CartItem> cartItens, User user, Address address, CorreiosServicoWrapper frete) {
		itens = new ArrayList<OrderItem>();
		
		for(CartItem cartItem : cartItens) {
			itens.add(new OrderItem(this, cartItem));
		}
		
		this.user = user;
		this.date = Calendar.getInstance();
		this.address = address;
		this.freteValue = frete.getValueAsBigDecimal();
		this.fretePrazo = frete.getPrazo();
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

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getItens() {
		return itens;
	}

	public void setItens(List<OrderItem> itens) {
		this.itens = itens;
	}

	public Calendar getDate() {
		return date;
	}
	
	public String getFormattedDate() {
		return (date == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date.getTime()));
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Address getAddress() {
		return address;
	}
	
	public Long getAddressId() {
		return (address == null ? 0 : address.getId());
	}
	
	public String getAddressStreet() {
		return (address == null ? "" : address.getStreet());
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BigDecimal getTotalValue() {
		BigDecimal total = ZERO;
		
		if(itens != null && itens.size() != 0) {
			for(OrderItem item : itens)
				total = total.add(item.getValue());
		}
		
		total = total.add(freteValue == null ? ZERO : freteValue);
		
		return total;
	}
	
	public String getFormattedTotalValue() {
		BigDecimal value = getTotalValue();
		
		return (value == null ? "" : format("R$ %.02f", value));
	}

	public BigDecimal getFreteValue() {
		return freteValue;
	}

	public void setFreteValue(BigDecimal freteValue) {
		this.freteValue = freteValue;
	}
	
	public String getFormattedFreteValue() {
		return (freteValue == null ? "" : format("R$ %.02f", freteValue));
	}

	public String getFretePrazo() {
		return fretePrazo;
	}

	public void setFretePrazo(String fretePrazo) {
		this.fretePrazo = fretePrazo;
	}

}