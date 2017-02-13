package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador {
	
	private Indicador outroIndicador;
	private int intervalo;

	public MediaMovelSimples(Indicador outroIndicador) {
		this.outroIndicador = outroIndicador;
	}
	
	public MediaMovelSimples(int intervalo) {
		this.intervalo = intervalo;
	}
	
	
	
	/* 
	 * Metodo calcula MMS, recebe a posicao da serie da qual a média será
	 * calculada e a própria série.
	 * 
	 */
	@Override
	public double calcula(int posicao, SerieTemporal serie) {
		// seta a soma para zero, iguala a posicao com i, e vai decrementando i
		// até buscar os ultimos n dias antes da posicao indicada.
		double soma = 0.0;
		for (int i = posicao; i > posicao - 3; i--) {
			// para cada candle, pega o valor do fechamento e incrementa o valor
			// a variavel 'soma'
			soma += outroIndicador.calcula(i, serie);
		}
		return soma / 3;
	}

		public double calculaIntervalo(int posicao, SerieTemporal serie, int intervalo) {
			// seta a soma para zero, iguala a posicao com i, e vai decrementando i
			// até buscar os ultimos n dias antes da posicao indicada.
			if (intervalo > posicao+1){
				intervalo = posicao+1;
			}
			
			double soma = 0.0;
			for (int i = posicao; i > posicao - intervalo; i--) {
				// para cada candle, pega o valor do fechamento e incrementa o valor
				// a variavel 'soma'
				soma += outroIndicador.calcula(i, serie);
			}
			return soma / intervalo;
	}
	
	@Override
	public String toString(){
		return "MMS de "+ outroIndicador.toString();		
	}
}
