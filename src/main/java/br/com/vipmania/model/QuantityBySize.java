package br.com.vipmania.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TB_QUANTITIES_BY_SIZE")
public class QuantityBySize {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private SizeItem sizeItem;
	
	private Long quantity;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SizeItem getSizeItem() {
		return sizeItem;
	}
	
	public Long getSizeItemId() {
		return (sizeItem == null ? 0 : sizeItem.getId());
	}
	
	public String getSizeItemName() {
		return (sizeItem == null ? "" : sizeItem.getName());
	}

	public void setSizeItem(SizeItem sizeItem) {
		this.sizeItem = sizeItem;
	}

	public Long getQuantity() {
		return (quantity == null ? 0 : quantity);
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public void reduce(Long quantity) {
		this.quantity -= quantity;
	}

	public boolean hasProduct() {
		return product != null;
	}
	
}