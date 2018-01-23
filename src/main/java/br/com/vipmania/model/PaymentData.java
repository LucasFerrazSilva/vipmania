package br.com.vipmania.model;

import java.math.BigDecimal;

public class PaymentData {

	private BigDecimal value;


	public PaymentData(BigDecimal value) {
		this.value = value;
	}
	
	public PaymentData() {
		// noop
	}
	
	
	public BigDecimal getValue() {
		return value;
	}
	
}