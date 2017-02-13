package br.com.caelum.argentum.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GeradorAleatorioDeXML {

	public static void main(String[] args) {
		Calendar data = Calendar.getInstance();

		Random random = new Random(123);

		List<Negociacao> negociacoes = new ArrayList<Negociacao>();

		double valor = 40;
		int quantidade = 1000;

		for (int dias = 0; dias < 30; dias++) {
			int quantidadeNegociacoesDoDia = random.nextInt(20);

			for (int negociacao = 0; negociacao < quantidadeNegociacoesDoDia; negociacao++) {
				// no máximo sobe ou cai R$1,00 e nao baixa além de R$5,00
				valor += (random.nextInt(200) - 100 / 100.0);

				if (valor < 5.0) {
					valor = 5.0;
				}
				// quantidade: entre 500 e 1500
				quantidade += 1000 - random.nextInt(500);

				Negociacao n = new Negociacao(valor, quantidade, data);
				negociacoes.add(n);
			}
			data = (Calendar) data.clone();
			data.add(Calendar.DAY_OF_YEAR, 1);
		}
		XStream stream = new XStream(new DomDriver());
		stream.alias("negociacao", Negociacao.class);
		stream.setMode(XStream.ID_REFERENCES); // pode criar ou Ids para as tags

		try {
			PrintStream out = new PrintStream(new File("negociacao.xml"));
			out.println(stream.toXML(negociacoes));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
