package br.com.vipmania.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.vipmania.wrapper.SizeItemWrapper;

@Entity
@Table(name="TB_SIZES")
public class Size {
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	private Long id;
	
	private String name;

	@OneToMany(mappedBy="size", cascade=ALL, orphanRemoval=true)
	private List<SizeItem> itens;


	public boolean isNew() {
		return (id == null);
	}
	
	public void removeEmptyItens() {
		for(Iterator<SizeItem> i = itens.iterator() ; i.hasNext() ; ) {
			SizeItem item = i.next();
			String itemName = item.getName();

			if(itemName == null || itemName.equals(""))
				i.remove();
		}
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

	public List<SizeItem> getItens() {
		return itens;
	}

	public List<SizeItem> getOrderedItens() {
		if(itens == null)
			return null;
		
		Collections.sort(itens, new Comparator<SizeItem>() {

			@Override
			public int compare(SizeItem item1, SizeItem item2) {
				return item1.getName().compareTo(item2.getName());
			}
		});
		
		return itens;
	}
	
	
	
	public List<SizeItemWrapper> getItemsWrappers(){
		List<SizeItemWrapper> wrappers = new ArrayList<SizeItemWrapper>();
		
		for(SizeItem item : itens)
			wrappers.add(new SizeItemWrapper(item));
		
		return wrappers;
	}

	public void setItens(List<SizeItem> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Size [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Size other = (Size) obj;
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
		return true;
	}

}