package br.com.caelum.argentum.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandleFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;

@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable {

	/**
	 * 
	 */
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String nomeMedia;
	private String nomeIndicadorBase;
	private String titulo;
	

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

	public ArgentumBean() {
		this.negociacoes = new ClienteWebService().getNegociacoes();
		geraGrafico();
	}

	public void geraGrafico() {
		System.out.println("Gerando Gráfico: " + nomeMedia + " de " + nomeIndicadorBase);
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);

		GeradorModeloGrafico geradorGrafico = new GeradorModeloGrafico(serie, 2, serie.getUltimaPosicao(), titulo);
				
		IndicadorFactory fabrica = new IndicadorFactory(nomeMedia, nomeIndicadorBase);
		
		//geradorGrafico.plotaIndicador(defineIndicador()); -- Usar essa chamada de metodo sem a Factory de Indicador
		geradorGrafico.plotaIndicador(fabrica.defineIndicador());
		System.out.println("Plotando Gráfico: " + nomeMedia + " de " + nomeIndicadorBase);
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}
}
