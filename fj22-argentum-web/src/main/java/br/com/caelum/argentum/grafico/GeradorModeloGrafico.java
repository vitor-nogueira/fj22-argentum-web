package br.com.caelum.argentum.grafico;

import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.SerieTemporal;

public class GeradorModeloGrafico {

	/**
	 * atributos: serie, comeco, fim e grafico. gerar com ctrl+1 conforme vc
	 * criar o construtor
	 */

	private SerieTemporal serie;
	private int comeco;
	private int fim;
	private LineChartModel modeloGrafico;
	private String titulo;

	public GeradorModeloGrafico(SerieTemporal serie, int comeco, int fim, String titulo) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
		this.titulo = titulo;
		// atribui o modelo do grafico como um objeto do tipo LineChartModel
		this.modeloGrafico = new LineChartModel();
	}

	/**
	 * metodo para plotar os dados da media simples para cada um dos itens
	 * indicados entre ´comeco´ e ´fim. adiciona a serie gerada no grafico, seta
	 * a posicao de legenda e titulo do grafico
	 * 
	 * @param indicador
	 *            TODO
	 */
	public void plotaIndicador(Indicador indicador) {
		System.out.println(indicador.toString());
		LineChartSeries chartSerie = new LineChartSeries(indicador.toString());

		for (int i = comeco; i <= fim; i++) {
			double valor = indicador.calcula(i, serie);
			chartSerie.set(i, valor);
		}

		this.modeloGrafico.addSeries(chartSerie);
		this.modeloGrafico.setLegendPosition("w");
		this.modeloGrafico.setTitle(titulo);
	}

	public ChartModel getModeloGrafico() {
		return this.modeloGrafico;
	}
}