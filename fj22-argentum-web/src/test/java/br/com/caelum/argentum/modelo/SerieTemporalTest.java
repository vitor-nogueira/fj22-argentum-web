package br.com.caelum.argentum.modelo;


import org.junit.Test;

public class SerieTemporalTest {

	// Teste que recebe a lista como null, esperando que o contrutor barre
	// esses argumentos com uma excecao.
	@Test(expected = IllegalArgumentException.class)
	public void naoCriaSerieTemporalComSerieNula() {
		new SerieTemporal(null);
	}

}
