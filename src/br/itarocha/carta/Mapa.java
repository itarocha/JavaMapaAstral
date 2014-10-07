package br.itarocha.carta;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
	private CabecalhoMapa cabecalho;
	private List<ItemAspecto> la = new ArrayList<ItemAspecto>();
	private List<Cuspide> cuspides = new ArrayList<Cuspide>();
	private List<PlanetaPosicao> posicoesPlanetas = new ArrayList<PlanetaPosicao>();


	public Mapa(CabecalhoMapa cabecalho){
		this.cabecalho = cabecalho;
	}
	
	public CabecalhoMapa getCabecalhoMapa(){
		return this.cabecalho;
	}
	
	public List<ItemAspecto> getListaAspectos(){
		return this.la;
	}
	
	public List<Cuspide> getListaCuspides(){
		return this.cuspides;
	}
	
	public List<PlanetaPosicao> getPosicoesPlanetas(){
		return this.posicoesPlanetas;
	}
	
}
