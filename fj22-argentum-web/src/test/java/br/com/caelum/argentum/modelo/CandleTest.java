package br.com.caelum.argentum.modelo;

import java.util.Calendar;

import static org.junit.Assert.*;
import org.junit.Test;

public class CandleTest {

	// Teste que recebe minimo maior que maximo, esperando que o contrutor barre
	// esses argumentos com uma excecao.
	@Test(expected = IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMaiorQueMinimo() {
		new Candle(10, 20, 20, 10, 10000, Calendar.getInstance());
	}

	// Teste que recebe data como null, esperando que o contrutor barre
	// esses argumentos com uma excecao.
	@Test(expected = IllegalArgumentException.class)
	public void naoCriaCandlestickComDataNula() {
		new Candle(10, 30, 15, 35, 10000, null);
	}

	// Teste que recebe data como null, esperando que o contrutor barre
	// esses argumentos com uma excecao.
	@Test(expected = IllegalArgumentException.class)
	public void naoCriaCandlestickComValorNulo() {
		new Candle(-1, 30, 15, 35, 10000, Calendar.getInstance());
	}

	// Teste que recebe abertura igual a fechamento
	// e verifica se a candle é de alta
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		Candle c = new Candle(30, 30, 15, 35, 10000, Calendar.getInstance());
		assertEquals(true, c.isAlta());
	}
}
