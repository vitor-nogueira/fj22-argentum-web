package br.com.caelum.argentum.modelo;

import java.util.List;

public class SerieTemporal {
	
	private final List<Candle> candles;
	
	//construtor que recebe uma lista de candles para montar uma serie temporal.
	public SerieTemporal (List<Candle> candles){
		if (candles == null) {
			throw new IllegalArgumentException("A série temporal não pode ser nula!");
		}
		this.candles = candles;
	}
	
	//Retorna a candle da posição i
	public Candle getCandle(int i){
		return this.candles.get(i);
	}
	
	//Retorna o index da ultima posicao da lista
		public int getUltimaPosicao(){
			return this.candles.size()-1;
		}
}
