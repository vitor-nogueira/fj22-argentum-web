package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class CandleFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();

		// criando as negociações
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		// criando a lista de negociações com as negociacoes criadas
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);

		// instanciando a fabrica de candle
		CandleFactory fabrica = new CandleFactory();

		// criando uma candle para a data de hoje;
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		// System.out.println("Testando Candlestick 1 :" + candle.toString());
		// Testa o valor esperado para cada método e sua precisão.
		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(42.3, candle.getFechamento(), 0.00001);
		assertEquals(39.8, candle.getMinimo(), 0.00001);
		assertEquals(45.0, candle.getMaximo(), 0.00001);
		assertEquals(16760.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void semNegociacaoGeraCandleComZeros() {
		Calendar hoje = Calendar.getInstance();

		// criando a lista de negociações vazia
		List<Negociacao> negociacoes = Arrays.asList();

		// instanciando a fabrica de candle
		CandleFactory fabrica = new CandleFactory();

		// criando uma candle para a data de hoje;
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		// System.out.println("Testando Candlestick 2 :" + candle.toString());

		assertEquals(0.0, candle.getAbertura(), 0.00001);
		assertEquals(0.0, candle.getFechamento(), 0.00001);
		assertEquals(0.0, candle.getMinimo(), 0.00001);
		assertEquals(0.0, candle.getMaximo(), 0.00001);
		assertEquals(0.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {

		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);

		// criando a lista de negociações com as negociacoes criadas
		List<Negociacao> negociacoes = Arrays.asList(negociacao1);

		// instanciando a fabrica de candle
		CandleFactory fabrica = new CandleFactory();

		// criando uma candle para a data de hoje;
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		// System.out.println("Testando Candlestick 3 :" + candle.toString());

		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(40.5, candle.getFechamento(), 0.00001);
		assertEquals(40.5, candle.getMinimo(), 0.00001);
		assertEquals(40.5, candle.getMaximo(), 0.00001);
		assertEquals(4050.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void negociacoesEmOrdemCrescenteDeValor() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(50, 100, hoje);
		Negociacao negociacao4 = new Negociacao(60.5, 100, hoje);

		// criando a lista de negociações com as negociacoes criadas
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);

		// instanciando a fabrica de candle
		CandleFactory fabrica = new CandleFactory();

		// criando uma candle para a data de hoje;
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		// System.out.println("Testando Candlestick 4 :" + candle.toString());

		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(60.5, candle.getFechamento(), 0.00001);
		assertEquals(40.5, candle.getMinimo(), 0.00001);
		assertEquals(60.5, candle.getMaximo(), 0.00001);
		assertEquals(19600.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void negociacoesEmOrdemDecrescenteDeValor() {
		Calendar hoje = Calendar.getInstance();

		Negociacao negociacao1 = new Negociacao(60.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(50.5, 100, hoje);
		Negociacao negociacao3 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao4 = new Negociacao(40.0, 100, hoje);

		// criando a lista de negociações com as negociacoes criadas
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4);

		// instanciando a fabrica de candle
		CandleFactory fabrica = new CandleFactory();

		// criando uma candle para a data de hoje;
		Candle candle = fabrica.constroiCandleParaData(hoje, negociacoes);

		// System.out.println("Testando Candlestick 5 :" + candle.toString());

		assertEquals(60.5, candle.getAbertura(), 0.00001);
		assertEquals(40.0, candle.getFechamento(), 0.00001);
		assertEquals(40.0, candle.getMinimo(), 0.00001);
		assertEquals(60.5, candle.getMaximo(), 0.00001);
		assertEquals(19600.0, candle.getVolume(), 0.00001);
	}

	@Test
	public void paraNegociacoesDeTresDiasDistintosGeraTresCandles() {
		Calendar hoje = Calendar.getInstance();

		// criando as negociações
		Negociacao negociacao1 = new Negociacao(40.5, 100, hoje);
		Negociacao negociacao2 = new Negociacao(45.0, 100, hoje);
		Negociacao negociacao3 = new Negociacao(39.8, 100, hoje);
		Negociacao negociacao4 = new Negociacao(42.3, 100, hoje);

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
		Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		Negociacao negociacao7 = new Negociacao(51.8, 100, depois);
		Negociacao negociacao8 = new Negociacao(52.3, 100, depois);

		// criando a lista de negociações com as negociacoes criadas
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4, negociacao5,
				negociacao6, negociacao7, negociacao8);

		// instanciando a fabrica de candle
		CandleFactory fabrica = new CandleFactory();

		// criando uma candle para a data de hoje;
		List<Candle> candles = fabrica.constroiCandles(negociacoes);

		// System.out.println("Candle 1: " + candles.get(0).toString());
		// System.out.println("Candle 2: " + candles.get(1).toString());
		// System.out.println("Candle 3: " + candles.get(2).toString());
		// System.out.println("Objeto 4: " + candles.get(3).toString());
		// System.out.println("Objeto 5: " + candles.get(4).toString());
		// System.out.println("Objeto 6: " + candles.get(5).toString());

		assertEquals(3, candles.size());
		assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);
	}

	@Test(expected = IllegalStateException.class)
	public void naoPermiteConstruirCandlesComNegociacoesForaDeOrdem() {
		Calendar hoje = Calendar.getInstance();

		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);

		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);

		// criando as negociações
		Negociacao negociacao1 = new Negociacao(40.5, 100, depois);
		Negociacao negociacao2 = new Negociacao(45.0, 100, depois);
		Negociacao negociacao3 = new Negociacao(39.8, 100, depois);
		Negociacao negociacao4 = new Negociacao(42.3, 100, amanha);
		Negociacao negociacao5 = new Negociacao(48.8, 100, amanha);
		Negociacao negociacao6 = new Negociacao(49.3, 100, amanha);
		Negociacao negociacao7 = new Negociacao(51.8, 100, hoje);
		Negociacao negociacao8 = new Negociacao(52.3, 100, hoje);
		Negociacao negociacao9 = new Negociacao(53.5, 100, hoje);

		// criando a lista de negociações com as negociacoes criadas
		List<Negociacao> negociacoes = Arrays.asList(negociacao1, negociacao2, negociacao3, negociacao4, negociacao5,
				negociacao6, negociacao7, negociacao8, negociacao9);

		// instanciando a fabrica de candle
		CandleFactory fabrica = new CandleFactory();

		// criando uma candle para a data de hoje;
		List<Candle> candles = fabrica.constroiCandles(negociacoes);

		System.out.println("Candle 1: " + candles.get(0).toString());
		System.out.println("Candle 2: " + candles.get(1).toString());
		System.out.println("Candle 3: " + candles.get(2).toString());
		// System.out.println("tamanho da lista "+ candles.size());
		// assertEquals(2, candles.size());
	}

}
