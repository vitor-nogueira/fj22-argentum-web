package br.com.caelum.argentum.ws;

import java.util.List;

import org.junit.Test;
import br.com.caelum.argentum.modelo.Negociacao;

public class ClienteWebServiceTest {

	private List<Negociacao> negociacoes;


	@Test
	public void carregaXmlDoWebService() {
		ClienteWebService cliente = new ClienteWebService();
		this.negociacoes = cliente.getNegociacoes();
		
	}
}
