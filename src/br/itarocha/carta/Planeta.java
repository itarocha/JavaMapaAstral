package br.itarocha.carta;

public class Planeta {
	private int id;
	private String sigla;
	private String nome;

	public Planeta(int id, String sigla, String nome){
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
