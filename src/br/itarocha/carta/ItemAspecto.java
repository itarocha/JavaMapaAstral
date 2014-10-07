package br.itarocha.carta;

public class ItemAspecto {
	private PlanetaAspecto planetaA;
	private PlanetaAspecto planetaB;

	public ItemAspecto(){
		this.planetaA = new PlanetaAspecto();
		this.planetaB = new PlanetaAspecto();
	}
	
	private String aspecto;

	public PlanetaAspecto getPlanetaA() {
		return planetaA;
	}
	public void setPlanetaA(PlanetaAspecto planetaA) {
		this.planetaA = planetaA;
	}
	public PlanetaAspecto getPlanetaB() {
		return planetaB;
	}
	public void setPlanetaB(PlanetaAspecto planetaB) {
		this.planetaB = planetaB;
	}
	public String getAspecto() {
		return aspecto;
	}
	public void setAspecto(String aspecto) {
		this.aspecto = aspecto;
	}
}
