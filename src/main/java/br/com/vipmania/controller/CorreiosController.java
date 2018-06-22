package br.com.vipmania.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.vipmania.service.FreteService;
import br.com.vipmania.wrapper.CorreiosServicoWrapper;

@Controller
public class CorreiosController {

	@Autowired
	private FreteService freteService;
	
	
	@ResponseBody
	@RequestMapping("/calcular-frete/{cep}")
	public CorreiosServicoWrapper calcularFrete(@PathVariable(value="cep") String cep) {
		return freteService.getServicoWrapper(cep);        
	}
	
	
}
