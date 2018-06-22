package br.com.vipmania.model;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TAB_ORDER_ITENS")
public class OrderItem {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Product product;
	
	@Column(name="quantity")
	private Long quantity;
	
	@ManyToOne
	private SizeItem sizeItem;
	

	public OrderItem() {
		//noop
	}
	
	public OrderItem(Product product, Long quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	
	public OrderItem(Order order, CartItem item) {
		this.order = order;
		this.product = item.getProduct();
		this.quantity = item.getQuantity();
		this.sizeItem = item.getSizeItem();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}
	
	public Long getProductId() {
		return (product == null ? 0 : product.getId());
	}
	
	public String getProductName() {
		return (product == null ? "" : product.getName());
	}
	
	public BigDecimal getProductValue() {
		return (product == null ? ZERO : product.getValue());
	}
	
	public String getFormattedProductValue() {
		BigDecimal value = getProductValue();
		
		return (value == null ? "" : format("R$ %.02f", value));
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}
	
	public QuantityBySize getQuantityBySize() {
		return (product == null || sizeItem == null ? null : product.getQuantityBySize(sizeItem));
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getValue() {
		return product.getValue().multiply(BigDecimal.valueOf(quantity));
	}
	
	public String getFormattedValue() {
		BigDecimal value = getValue();
		
		return (value == null ? "" : format("R$ %.02f", value));
	}

	public SizeItem getSizeItem() {
		return sizeItem;
	}
	
	public String getSizeItemName() {
		return (sizeItem == null ? "" : sizeItem.getName());
	}

	public void setSizeItem(SizeItem sizeItem) {
		this.sizeItem = sizeItem;
	}
	
}