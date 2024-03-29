package com.springbatch.contasbancarias.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.contasbancarias.dominio.Cliente;
import com.springbatch.contasbancarias.dominio.Conta;

public class ContaInvalidaItemProcessor implements ItemProcessor<Cliente, Conta> {

	@Override
	public Conta process(Cliente item) throws Exception {
		Conta c = new Conta();
		c.setClienteId(item.getEmail());
		return c;
	}

}
