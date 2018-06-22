package br.com.vipmania.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.vipmania.model.xml.CorreiosServicos;
import br.com.vipmania.wrapper.CorreiosServicoWrapper;

@Component
public class FreteService {

	@Autowired
	private RestTemplate restTemplate;
	
	
	public CorreiosServicoWrapper getServicoWrapper(String cep) {
        try {
        	String url = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa=&sDsSenha=&sCepOrigem=06341170&sCepDestino=CEP_DESTINO&nVlPeso=1&nCdFormato=1&nVlComprimento=20&nVlAltura=20&nVlLargura=20&sCdMaoPropria=n&nVlValorDeclarado=0&sCdAvisoRecebimento=n&nCdServico=04510&nVlDiametro=0&StrRetorno=xml&nIndicaCalculo=3".replace("CEP_DESTINO", cep);
        	
        	String result = restTemplate.getForObject(url, String.class);
        	
        	CorreiosServicos servicos = new XmlMapper().readValue(result, CorreiosServicos.class);
        	
        	return servicos.getWrapper();
        }
        catch(Exception e) {
        	System.out.println("Erro ao calcular frete");
        	return new CorreiosServicoWrapper("10", "10");
        }
	}
	
}