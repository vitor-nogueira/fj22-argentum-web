package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandleFactory {

	public Candle constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {

		// Inicia as variaveis com o preco da primeira negociacao.
		// Seta o volume para zero.

		double maximo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double minimo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double volume = 0;

		// para cada negociacao da lista de negociações, faça
		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume(); // adiciona o volume da negociação
												// ao volume total das
												// negociações

			// se o valor da negociacao maior que maximo, atualiza o valor de
			// maximo
			double preco = negociacao.getPreco();
			if (preco > maximo) {
				maximo = preco;
				// se o valor da negociacao menor que minimo, atualiza o valor
				// de minimo
			} else if (preco < minimo) {
				minimo = preco;
			}
		}

		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();

		// Retorna o objeto Candlestick montado com os valores;
		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}

	public List<Candle> constroiCandles(List<Negociacao> todasNegociacoes) {
		// TODO Auto-generated method stub
		List<Candle> candles = new ArrayList <Candle> ();
		
		List<Negociacao> negociacoesDoDia = new ArrayList<Negociacao>();
		
		Calendar dataAtual = todasNegociacoes.get(0).getData();
		
		for (Negociacao negociacao : todasNegociacoes) {
			if(negociacao.getData().before(dataAtual)){
				throw new IllegalStateException("Negociacao em ordem errada");
			}
			
			//se a negociacao nao for no mesmo dia, fecha a candle e reinicia a variável
			if (!negociacao.isMesmoDia(dataAtual)){
				criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
				
				negociacoesDoDia = new ArrayList<Negociacao>();
				dataAtual = negociacao.getData();
			}
			negociacoesDoDia.add(negociacao);
		}
		criaEGuardaCandle(candles, negociacoesDoDia, dataAtual);
		
		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles, List<Negociacao> negociacoesDoDia, Calendar dataAtual) {
		Candle candleDoDia = constroiCandleParaData(dataAtual, negociacoesDoDia);
		candles.add(candleDoDia);
	}
}
