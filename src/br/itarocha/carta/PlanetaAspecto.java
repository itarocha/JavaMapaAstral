package br.itarocha.carta;

public class PlanetaAspecto {
	private Integer planeta;
	private int coordenada;
	private double posicao;
	private String grau;
	private String sigla;
	
	public int getPlaneta() {
		return planeta;
	}
	public void setPlaneta(Integer planeta) {
		this.planeta = planeta;
	}
	public int getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(int coordenada) {
		this.coordenada = coordenada;
	}
	public double getPosicao() {
		return posicao;
	}
	public void setPosicao(double posicao) {
		this.posicao = posicao;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
