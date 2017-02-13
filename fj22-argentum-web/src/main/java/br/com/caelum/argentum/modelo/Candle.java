package br.com.caelum.argentum.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Candle {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	//cria��o do construtor para recebimento dos campos com teste se campo data n�o � null
	public Candle(double abertura, double fechamento, double minimo, double maximo, double volume, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("Os valores nao podem ser negativos!");
		}
		
		if (abertura < 0 || fechamento < 0 || minimo < 0 || maximo < 0 || volume < 0){
			throw new IllegalArgumentException("Data da Candlestick n�o pode ser nula");
		}
		
		if (minimo > maximo ){
			throw new IllegalArgumentException("M�nimo nao pode ser maior que m�ximo");
		}
		
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}
	

	//Getters and Setters
	public double getAbertura() {
		return abertura;
	}


	public double getFechamento() {
		return fechamento;
	}


	public double getMinimo() {
		return minimo;
	}


	public double getMaximo() {
		return maximo;
	}


	public double getVolume() {
		return volume;
	}


	public Calendar getData() {
		return data;
	}
	
	// Reescrita do m�todo toString que coloca em string o objeto com a formatacao abaixo
	@Override
	public String toString() {
		String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
		return "[Abertura: "+ abertura + ", Fechamento = "+fechamento+
				", M�nimo :" +minimo+ ", M�ximo: "+maximo+ ", Total: "+volume+", Data: "+dataFormatada+"]";
	}
				
	
	//isAlta se valor no fechamento � maior que valor na abertura
	public boolean isAlta() {
	return this.abertura <= this.fechamento;
	}
	
	//isBaixa se valor na abertura � maior que valor no fechamento
	public boolean isBaixa() {
		return this.abertura > this.fechamento;
		}
}
