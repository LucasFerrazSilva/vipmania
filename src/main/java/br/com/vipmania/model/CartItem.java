package br.com.vipmania.model;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long productId;
	private Product product;
	private Long quantity;
	
	
	public CartItem(Product product, Long quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1L;
	}


	public String getName() {
		return (product == null ? "" : product.getName());
	}
	
	public BigDecimal getValue() {
		return (product == null ? ZERO : product.getValue());
	}
	
	public BigDecimal getTotalValue() {
		if(product == null)
			return ZERO;
		
		BigDecimal value = product.getValue();
		
		value.setScale(2, HALF_UP);
		
		return (value == null ? ZERO : (value.multiply(new BigDecimal(quantity))));
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity(Long quantity) {
		this.quantity += quantity;
	}
	
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	public boolean containsProduct(Product product) {
		return (this.product.equals(product));
	}

}