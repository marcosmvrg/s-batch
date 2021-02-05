package com.curso.faturacartaocredito.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.curso.faturacartaocredito.dominio.Cliente;
import com.curso.faturacartaocredito.dominio.FaturaCartaoCredito;

@Component
public class CarregaDadoClienteProcessorConfig implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public FaturaCartaoCredito process(FaturaCartaoCredito item) throws Exception {
		String uri = String.format("http://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d", 
				item.getCliente().getId());
		
		ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);
		
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new ValidationException("Cliente não encontrado");			
		}
		
		item.setCliente(response.getBody());
		return item;
	}
	
	

}
