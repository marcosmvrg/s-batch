package com.curso.enviopromocoes.processor;

import java.text.NumberFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.curso.enviopromocoes.domain.InteresseProdutoCliente;

@Component
public class ProcessaEmailProdutoClienteProcessor implements ItemProcessor<InteresseProdutoCliente, SimpleMailMessage>{

	@Override
	public SimpleMailMessage process(InteresseProdutoCliente item) throws Exception {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("xpto@no-replay.com");
		sm.setTo(item.getCliente().getEmail());
		sm.setSubject("Promoção imperdível");
		sm.setText(geraTextoPromocao(item));
		
		//for test only
		Thread.sleep(2000L);
		
		return sm;
	}

	private String geraTextoPromocao(InteresseProdutoCliente item) {
		return new StringBuilder(String.format("Olá %s! \n\n", item.getCliente().getNome()))
				.append("Essa promoção pode ser do seu interesse!\n\n")
				.append(String.format("%s - %s \n\n", item.getProduto().getNome(), item.getProduto().getDescricao()))
				.append(String.format("Por apenas: %s!", 
						NumberFormat.getCurrencyInstance().format(item.getProduto().getPreco())))
				.toString();
	}

}
