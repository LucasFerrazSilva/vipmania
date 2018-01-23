package br.com.vipmania.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	private String category;
	
	private Calendar validFrom;
	
	private Calendar validTo;
	
	private String photoPath;
	

	public boolean hasInvalidValue() {
		return (value == null || value.doubleValue() < 0);
	}

	private String formatDate(Calendar calendar) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		return (calendar == null ? "" : format.format(calendar.getTime()));
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
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getValue() {
		return value;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photoPath == null) ? 0 : photoPath.hashCode());
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", value=" + value
				+ ", category=" + category + ", validFrom=" + validFrom + ", validTo=" + validTo + ", photo=" + photoPath
				+ "]";
	}
			
}