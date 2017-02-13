package br.com.caelum.argentum.indicadores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorDeSerie {
	/**
	 * Serve para ajudar a fazer os testes. Recebe uma sequencia de valores e
	 * cria candles com abertura, fechamento, minimo e maximo iguais, mil de
	 * volume e a data de hoje. Finalmente, devolve tais candles, encapsuladas
	 * em uma serie temporal.
	 */

	public static SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<Candle>();
		for (double d : valores) {
			candles.add(new Candle(d, d, d, d, 1000, Calendar.getInstance()));
		}
		return new SerieTemporal(candles);
	}

}
