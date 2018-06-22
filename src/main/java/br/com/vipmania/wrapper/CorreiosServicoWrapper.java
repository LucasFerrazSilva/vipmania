package br.com.vipmania.wrapper;

import java.math.BigDecimal;

import br.com.vipmania.model.xml.CorreiosServico;

public class CorreiosServicoWrapper {

	private String valor;
	
	private String prazo;
	
	
	public CorreiosServicoWrapper(String valor, String prazo) {
		this.valor = valor;
		this.prazo = prazo;
	}

	public CorreiosServicoWrapper(CorreiosServico servico) {
		this(servico.getValor(), servico.getPrazoEntrega());
	}
	

	public String getValor() {
		return valor;
	}
	
	public String getValue() {
		return (valor == null ? "0.00" : valor.replace(",", "."));
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getPrazo() {
		return prazo;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public BigDecimal getValueAsBigDecimal() {
		return new BigDecimal(getValue());
	}
	
}