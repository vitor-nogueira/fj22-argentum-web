package br.com.caelum.argentum.bean;

import java.lang.reflect.Constructor;

import br.com.caelum.argentum.indicadores.Indicador;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;

public class IndicadorFactory {

	// ?
	private String nomeIndicadorBase;
	private String nomeMedia;

	public IndicadorFactory(String nomeMedia, String nomeIndicadorBase) {
		this.nomeMedia = nomeMedia;
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

	/**
	 * Metodo para definir o indicador que será utilizado para gerar o gráfico.
	 * 
	 * @return o indicador que foi selecionado na JSF.
	 */
	Indicador defineIndicador() {
		if (nomeIndicadorBase == null || nomeMedia == null) {
			return new MediaMovelSimples(new IndicadorFechamento());
		}
		try {
			// Define o pacote onde a classe será buscada
			String pacote = "br.com.caelum.argentum.indicadores.";

			// variavel classeIndicadorBase do tipo Class<?> que armazenará
			// a classe do Indicador Base escolhido.
			Class<?> classeIndicadorBase;

			// Class.forName salva em classeIndicadorBase o nome da classe do
			// indicador base escolhido
			System.out.println(nomeIndicadorBase);
			classeIndicadorBase = Class.forName(pacote + nomeIndicadorBase);

			// instancia um objeto do tipo Indicador com o indicadorBase
			// escolhido na pagina
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();

			// variavel classeMedia do tipo Class<?> que armazenará
			// a classe da media escolhida na página.
			System.out.println(nomeMedia);
			Class<?> classeMedia = Class.forName(pacote + nomeMedia);

			// variavel construtorMedia do tipo Construtor<?> que armazenará
			// o construtor para a media escolhida na página.
			Constructor<?> construtorMedia = classeMedia.getConstructor(Indicador.class);
			// instancia um objeto do tipo Indicador com o construtor recuperado
			// pelo getConstrutos e
			// pelo indicadorBase escolhido na pagina
			Indicador indicador = (Indicador) construtorMedia.newInstance(indicadorBase);
			return indicador;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
