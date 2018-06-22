package br.com.vipmania.model.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import br.com.vipmania.wrapper.CorreiosServicoWrapper;

@JacksonXmlRootElement(localName="Servicos")
public class CorreiosServicos {

	@JacksonXmlElementWrapper(localName="cServico", useWrapping=false)
	private CorreiosServico[] cServico;
	
	
	public CorreiosServicos() {
		//noop
	}
	
	public CorreiosServicos(CorreiosServico[] cServico) {
		this.cServico = cServico;
	}

	
	public CorreiosServico[] getcServico() {
		return cServico;
	}
	
	

	public void setcServico(CorreiosServico[] cServico) {
		this.cServico = cServico;
	}

	@Override
	public String toString() {
		return "CorreiosServicos [cServico=" + Arrays.toString(cServico) + "]";
	}

	public List<CorreiosServicoWrapper> getWrappers() {
		List<CorreiosServicoWrapper> wrappers = new ArrayList<CorreiosServicoWrapper>();
		
		for(CorreiosServico servico : cServico)
			wrappers.add(new CorreiosServicoWrapper(servico));
		
		return wrappers;
	}

	public CorreiosServicoWrapper getWrapper() {
		if(cServico == null)
			return null;
		
		return cServico[0].getWrapper();
	}
	
}