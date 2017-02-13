package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador {
	
	private Indicador outroIndicador;

	public MediaMovelPonderada(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
	}
	
	//metodo calcula MMS, recebe a posicao da serie da qual a média será calculada e a própria série.
	
		public double calcula (int posicao, SerieTemporal serie){
			//seta a soma para zero, iguala a posicao com i, e vai decrementando i até buscar os ultimos
			// tres dias antes da posicao indicada.
			//o peso é multiplicado na soma,e é decrementado.
			double soma = 0.0;
			int peso = 3;
			for (int i = posicao; i> posicao -3; i--){
				// para cada candle, pega o valor do fechamento e incrementa o valor a variavel 'soma'
				Candle c = serie.getCandle(i);
				soma += outroIndicador.calcula(i, serie);
				//soma += c.getFechamento()*peso;
				peso--;
			}
			return soma/6;
		}
		@Override
		public String toString(){
			return "MMP de " + outroIndicador.toString();		
		}
}
