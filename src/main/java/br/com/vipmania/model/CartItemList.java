package br.com.vipmania.model;

import java.util.List;

import br.com.vipmania.dao.ProductDAO;

public class CartItemList {

	private List<CartItem> itens;
	
	
	public List<CartItem> getItens(ProductDAO productDao){
		if(itens != null && itens.size() != 0) {
			Product product = null;
			
			for(CartItem item : itens) {
				product = item.getProduct();
				
				if(product != null){
					product = productDao.get(product.getId());
					item.setProduct(product);
				}
			}
		}
		
		return itens;
	}
	
	public List<CartItem> getItens(){
		return itens;
	}
	
	public void setItens(List<CartItem> itens) {
		this.itens = itens;
	}
	
}