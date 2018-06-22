package br.com.vipmania.model;

import static java.lang.String.format;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TAB_PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private BigDecimal value;
	
	@ManyToOne
	private Category category;
	
	private Calendar validFrom;
	
	private Calendar validTo;
	
	private String photoPath;
	
	@ManyToOne
	private Size size;

	@OneToMany(mappedBy="product", cascade=ALL, fetch=EAGER)
	private List<QuantityBySize> quantities;
	
	
	public Product() {
		updateProductAtQuantities();
	}
	

	public boolean hasInvalidValue() {
		return (value == null || value.doubleValue() < 0);
	}

	private String formatDate(Calendar calendar) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		return (calendar == null ? "" : format.format(calendar.getTime()));
	}
	

	public boolean isNew() {
		return (id == null);
	}
	
	public boolean isAvailable() {
		if(quantities == null || quantities.size() == 0)
			return false;
		
		for(QuantityBySize quantity : quantities) {
			if(quantity.getQuantity() > 0)
				return true;
		}
		
		return false;		
	}
	
	public void updateProductAtQuantities() {
		if(quantities == null)
			return;
		
		for (QuantityBySize quantityBySize : quantities) {
			if(!quantityBySize.hasProduct())
				quantityBySize.setProduct(this);
		}
	}

	public boolean isBuyable() {
		return validFrom.before(Calendar.getInstance()) && (validTo == null || validTo.after(Calendar.getInstance()));
	}
	
	public Long getMaxQuantity(SizeItem sizeItem) {
		QuantityBySize size = getQuantityBySize(sizeItem);
		
		return (size == null ? null : size.getQuantity());
	}
	
	public QuantityBySize getQuantityBySize(SizeItem sizeItem) {
		for(QuantityBySize quantity : quantities) {
			if(quantity.getSizeItem().equals(sizeItem))
				return quantity;
		}
		
		return null;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getFormattedDescription() {
		return (description == null ? "" : description.replace("\r\n", "<br />"));
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Category getCategory() {
		return category;
	}
	
	public String getCategoryName() {
		return (category == null ? "" : category.getName());
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getValue() {
		return value;
	}
	
	public String getFormattedValue() {
		return (value == null ? "" : format("R$ %.02f", value));
	}
	
	public Calendar getValidFrom() {
		return validFrom;
	}
	
	public String getFormattedValidFrom() {
		return formatDate(validFrom);
	}
	
	public void setValidFrom(Calendar validFrom) {
		this.validFrom = validFrom;
	}

	public Calendar getValidTo() {
		return validTo;
	}
	
	public String getFormattedValidTo() {
		return formatDate(validTo);
	}

	public void setValidTo(Calendar validTo) {
		this.validTo = validTo;
	}
	

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	
	public List<QuantityBySize> getQuantities() {
		return quantities;
	}
	
	public List<SizeItem> getAvailableSizes(){
		if(quantities == null)
			return null;
		
		List<SizeItem> items = new ArrayList<SizeItem>();
		
		for(QuantityBySize quantityBySize : quantities) {
			if(quantityBySize.getQuantity() > 0)
				items.add(quantityBySize.getSizeItem());
		}
		
		return items;
	}
	
	public QuantityBySize getQuantity(Long sizeItemId) {
		if(quantities == null || sizeItemId == null)	
			return null;
		
		for(QuantityBySize item : quantities)
			if(sizeItemId.equals(item.getSizeItemId()))
					return item;
		
		return null;
	}

	public void setQuantities(List<QuantityBySize> quantities) {
		this.quantities = quantities;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", value=" + value
				+ ", category=" + category + ", validFrom=" + validFrom + ", validTo=" + validTo + ", photoPath="
				+ photoPath + ", size=" + size + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photoPath == null) ? 0 : photoPath.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());
		result = prime * result + ((validTo == null) ? 0 : validTo.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photoPath == null) {
			if (other.photoPath != null)
				return false;
		} else if (!photoPath.equals(other.photoPath))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (validFrom == null) {
			if (other.validFrom != null)
				return false;
		} else if (!validFrom.equals(other.validFrom))
			return false;
		if (validTo == null) {
			if (other.validTo != null)
				return false;
		} else if (!validTo.equals(other.validTo))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}