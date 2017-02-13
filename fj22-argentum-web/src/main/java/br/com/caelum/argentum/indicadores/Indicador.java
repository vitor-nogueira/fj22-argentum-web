package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public interface Indicador {

	double calcula(int posicao, SerieTemporal serie);
	//double calculaIntervalo(int posicao, SerieTemporal serie, int intervalo);


}