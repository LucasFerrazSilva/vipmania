package br.com.vipmania.model;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long productId;
	private Product product;
	private Long quantity;
	private SizeItem sizeItem;
	

	public CartItem(Product product) {
		this.product = product;
		this.quantity = 1L;
	}
	
	
	public CartItem() {
		//noop
	}

	
	public boolean hasInvalidQuantity() {
		return quantity > getMaxQuantity();
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
	
	public Long getMaxQuantity() {
		return (product == null ? null : product.getMaxQuantity(sizeItem));
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
		return (product == null ? productId : product.getId());
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public SizeItem getSizeItem() {
		return sizeItem;
	}
	
	public Long getSizeItemId() {
		return (sizeItem == null ? null : sizeItem.getId());
	}
	
	public String getSizeItemName() {
		return (sizeItem == null ? "" : sizeItem.getName());
	}

	public void setSizeItem(SizeItem sizeItem) {
		this.sizeItem = sizeItem;
	}


		public boolean containsProduct(Product product) {
		return (this.product.equals(product));

	}
	
	public String getFormattedValue() {
		BigDecimal value = getValue();
		
		return (value == null ? "" : format("R$ %.02f", value));
	}
	
	public String getFormattedTotalValue() {
		BigDecimal value = getTotalValue();
		
		return (value == null ? "" : format("R$ %.02f", value));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((sizeItem == null) ? 0 : sizeItem.hashCode());
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
		if (sizeItem == null) {
			if (other.sizeItem != null)
				return false;
		} else if (!sizeItem.equals(other.sizeItem))
			return false;
		return true;
	}


}