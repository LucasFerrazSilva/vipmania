package br.com.vipmania.model;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value=SCOPE_SESSION, proxyMode=TARGET_CLASS)
public class Cart implements Serializable{

	private static final long serialVersionUID = -561709117951144616L;
	
	private List<CartItem> itens = new ArrayList<CartItem>();
	
	
	public void add(CartItem item) {
		if(itens.contains(item)) {
			int index = itens.indexOf(item);
			
			CartItem existingItem = itens.get(index);
			
			existingItem.addQuantity(item.getQuantity());
		}
		else {
			itens.add(item);
		}
	}

	public List<CartItem> getItens() {
		return itens;
	}
	
	public int getQuantity() {
		return itens.size();
	}
	
	public BigDecimal getTotalValue() {
		BigDecimal totalValue = ZERO;

		totalValue.setScale(2, HALF_UP);
		
		for(CartItem item : itens)
			totalValue = totalValue.add(item.getValue());
		
		return totalValue;
	}

	public void remove(Product product) {
		itens.remove(new CartItem(product));
	}
}
