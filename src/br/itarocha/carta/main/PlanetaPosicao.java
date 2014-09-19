package br.itarocha.carta.main;

public class PlanetaPosicao {
	private String nomePlaneta;
	private String nomeSigno;
	private double posicao;
	private boolean retrogrado;
	private double latitude;
	private double distancia;
	private double direcao;
	public String getNomePlaneta() {
		return nomePlaneta;
	}
	public void setNomePlaneta(String nomePlaneta) {
		this.nomePlaneta = nomePlaneta;
	}
	public String getNomeSigno() {
		return nomeSigno;
	}
	public void setNomeSigno(String nomeSigno) {
		this.nomeSigno = nomeSigno;
	}
	public double getPosicao() {
		return posicao;
	}
	public void setPosicao(double posicao) {
		this.posicao = posicao;
	}
	public boolean isRetrogrado() {
		return retrogrado;
	}
	public void setRetrogrado(boolean retrogrado) {
		this.retrogrado = retrogrado;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public double getDirecao() {
		return direcao;
	}
	public void setDirecao(double direcao) {
		this.direcao = direcao;
	}

}
