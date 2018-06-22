package br.com.vipmania.wrapper;

import br.com.vipmania.model.SizeItem;

public class SizeItemWrapper {

	private Long id;
	
	private String name;
	
	
	public SizeItemWrapper(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public SizeItemWrapper(SizeItem item) {
		this(item.getId(), item.getName());
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

}