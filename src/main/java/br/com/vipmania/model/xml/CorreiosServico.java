package br.com.vipmania.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import br.com.vipmania.wrapper.CorreiosServicoWrapper;

public class CorreiosServico {

	@JacksonXmlProperty(localName="Codigo")
	private String codigo;

	@JacksonXmlProperty(localName="Valor")
	private String valor;

	@JacksonXmlProperty(localName="PrazoEntrega")
	private String prazoEntrega;

	@JacksonXmlProperty(localName="ValorSemAdicionais")
	private String valorSemAdicionais;

	@JacksonXmlProperty(localName="ValorMaoPropria")
	private String valorMaoPropria;

	@JacksonXmlProperty(localName="ValorAvisoRecebimento")
	private String valorAvisoRecebimento;

	@JacksonXmlProperty(localName="ValorValorDeclarado")
	private String valorValorDeclarado;

	@JacksonXmlProperty(localName="EntregaDomiciliar")
	private String entregaDomiciliar;

	@JacksonXmlProperty(localName="EntregaSabado")
	private String entregaSabado;

	@JacksonXmlProperty(localName="Erro")
	private String erro;

	@JacksonXmlProperty(localName="MsgErro")
	private String msgErro;

	@JacksonXmlProperty(localName="obsFim")
	private String obsFim;
	
	
	public CorreiosServico() {
		//noop
	}
	
	public CorreiosServico(String codigo, String valor, String prazoEntrega, String valorSemAdicionais, String valorMaoPropria, String valorAvisoRecebimento, String valorValorDeclarado, 
							String entregaDomiciliar, String entregaSabado, String erro, String msgErro, String obsFim) {
		this.codigo = codigo;
		this.valor = valor;
		this.prazoEntrega = prazoEntrega;
		this.valorSemAdicionais = valorSemAdicionais;
		this.valorMaoPropria = valorMaoPropria;
		this.valorAvisoRecebimento = valorAvisoRecebimento;
		this.valorValorDeclarado = valorValorDeclarado;
		this.entregaDomiciliar = entregaDomiciliar;
		this.entregaSabado = entregaSabado;
		this.erro = erro;
		this.msgErro = msgErro;
		this.obsFim = obsFim;
	}


	public CorreiosServicoWrapper getWrapper() {
		return new CorreiosServicoWrapper(this);
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getPrazoEntrega() {
		return prazoEntrega;
	}

	public void setPrazoEntrega(String prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}

	public String getValorSemAdicionais() {
		return valorSemAdicionais;
	}

	public void setValorSemAdicionais(String valorSemAdicionais) {
		this.valorSemAdicionais = valorSemAdicionais;
	}

	public String getValorMaoPropria() {
		return valorMaoPropria;
	}

	public void setValorMaoPropria(String valorMaoPropria) {
		this.valorMaoPropria = valorMaoPropria;
	}

	public String getValorAvisoRecebimento() {
		return valorAvisoRecebimento;
	}

	public void setValorAvisoRecebimento(String valorAvisoRecebimento) {
		this.valorAvisoRecebimento = valorAvisoRecebimento;
	}

	public String getValorValorDeclarado() {
		return valorValorDeclarado;
	}

	public void setValorValorDeclarado(String valorValorDeclarado) {
		this.valorValorDeclarado = valorValorDeclarado;
	}

	public String getEntregaDomiciliar() {
		return entregaDomiciliar;
	}

	public void setEntregaDomiciliar(String entregaDomiciliar) {
		this.entregaDomiciliar = entregaDomiciliar;
	}

	public String getEntregaSabado() {
		return entregaSabado;
	}

	public void setEntregaSabado(String entregaSabado) {
		this.entregaSabado = entregaSabado;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	public String getObsFim() {
		return obsFim;
	}

	public void setObsFim(String obsFim) {
		this.obsFim = obsFim;
	}

	@Override
	public String toString() {
		return "CorreiosServico [codigo=" + codigo + ", valor=" + valor + ", prazoEntrega=" + prazoEntrega
				+ ", valorSemAdicionais=" + valorSemAdicionais + ", valorMaoPropria=" + valorMaoPropria
				+ ", valorAvisoRecebimento=" + valorAvisoRecebimento + ", valorValorDeclarado=" + valorValorDeclarado
				+ ", entregaDomiciliar=" + entregaDomiciliar + ", entregaSabado=" + entregaSabado + ", erro=" + erro
				+ ", msgErro=" + msgErro + ", obsFim=" + obsFim + "]";
	}
	
}