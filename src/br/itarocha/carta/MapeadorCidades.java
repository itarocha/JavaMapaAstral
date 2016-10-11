package br.itarocha.carta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class MapeadorCidades {

	private List<Cidade> listaCidades = new ArrayList<Cidade>();

	public MapeadorCidades() {
		this.carregarArquivoCidades();
	}

	private void carregarArquivoCidades() {

		String arquivoCSV = "ephe/cidades_brasil.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {

			br = new BufferedReader(new FileReader(arquivoCSV));
			int i = -1;
			while (((linha = br.readLine()) != null)) {
				i++;
				if (i > 0) {
					/*
					 * Ler o arquivo //ephe//cidades_brasil.csv
					 * "codigo","lat","e/w","lon","n/s","nome sem acento"
					 * ,"uf","nome original","altitude","area"
					 * 1,"-16.45.26","W","-49.26.15","S","abadia de goias"
					 * ,"GO","Abadia de Goiás",898.000,136.900
					 * 2,"-18.29.08","W","-47.24.11","S","abadia dos dourados"
					 * ,"MG","Abadia dos Dourados",742.000,897.400
					 * 3,"-16.12.15","W","-48.42.25","S","abadiania","GO",
					 * "Abadiânia",1052.000,1047.700
					 */
					String[] cidade = linha.split(csvDivisor);

					Cidade c = new Cidade();
					c.setCodigo(Integer.parseInt(cidade[0]));
					c.setNomeSemAcento(cidade[5].replaceAll("\"", ""));
					c.setUF(cidade[6].replaceAll("\"", ""));
					c.setNomeOriginal(cidade[7].replaceAll("\"", ""));
					c.setLatitude(cidade[1].replaceAll("\"", ""));
					c.setLongitude(cidade[3].replaceAll("\"", ""));

					this.listaCidades.add(c);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Cidade getCidade(String nome, String uf) {
		Cidade retorno = new Cidade();
		String nomeSemAcento = removerAcentos(nome).toLowerCase();
		uf = uf.toLowerCase();
		for (Cidade c : this.listaCidades) {
			if (c.getNomeSemAcento().equals(nomeSemAcento) && c.getUF().toLowerCase().equals(uf)) {
				retorno = c;
				break;
			}
		}
		return retorno;
	}

	private String removerAcentos(String acentuada) {
		CharSequence cs = new StringBuilder(acentuada);
		return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

}
