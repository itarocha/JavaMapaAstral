package br.itarocha.carta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Mapa {
	//private CabecalhoMapa cabecalho;
	private String nome;
	private String nomeCidade;
	
	
	private Calendar calendar;
	private Coordenada latitude;
	private Coordenada longitude;
	private String data; // 
	private String hora; //
	private int fuso;
	
	private List<ItemAspecto> la = new ArrayList<ItemAspecto>();
	private List<Cuspide> cuspides = new ArrayList<Cuspide>();
	private List<PlanetaPosicao> posicoesPlanetas = new ArrayList<PlanetaPosicao>();

	/*
	public Mapa(CabecalhoMapa cabecalho){
		this.cabecalho = cabecalho;
	}
	*/
	
    /// <summary>
    /// Construtor do Cabeçalho do Mapa. 
    /// </summary>
    /// <param name="data">Data no formato yyyy.mm.dd</param>
    /// <param name="hora">Hora no formato hh.mm.ss</param>
    /// <param name="fuso">Fuso horário</param>
    /// <returns>The converted string</returns>
	public Mapa(String data, String hora, int fuso, String lat, String lon){
		int a = 0, m = 0, d = 0, h = 0, n = 0, s = 0;		
		int latdeg = 0, latmin = 0, latsec = 0;
		int londeg = 0, lonmin = 0, lonsec = 0;
		
        String[] sdata = data.split("\\.");
        try {
            a = Integer.parseInt(sdata[0]);
            m = Integer.parseInt(sdata[1]);
            d = Integer.parseInt(sdata[2]);
        }
        catch(Exception e) {
            //x = -999;  // TODO better error handling
        }
		
        String[] shora = hora.split("\\.");
        try {
            h = Integer.parseInt(shora[0]);
            n = Integer.parseInt(shora[1]);
            s = Integer.parseInt(shora[2]);
        }
        catch(Exception e) {
            //x = -999;  // TODO better error handling
        }
        
        // Mes base 0 ZEROOOOOO. Janeiro = 0; Fevereiro = 1
		this.calendar = new GregorianCalendar(a,m-1,d,h,n,s);
		
        String[] slat = lat.split("\\.");
        try {
            latdeg = Integer.parseInt(slat[0]);
            latmin = Integer.parseInt(slat[1]);
            latsec = Integer.parseInt(slat[2]);
        }
        catch(Exception e) {
            //x = -999;  // TODO better error handling
        }
		
        String[] slon = lon.split("\\.");
        try {
            londeg = Integer.parseInt(slon[0]);
            lonmin = Integer.parseInt(slon[1]);
            lonsec = Integer.parseInt(slon[2]);
        }
        catch(Exception e) {
            //x = -999;  // TODO better error handling
        }
		this.latitude = new Coordenada(latdeg, latmin, latsec);
		this.longitude = new Coordenada(londeg, lonmin, lonsec);
		
		this.fuso = fuso;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	
	public Calendar getCalendar(){
		return this.calendar;
	}
	
	public double getHoraDouble(){
		int h = calendar.get(Calendar.HOUR);
		int m = calendar.get(Calendar.MINUTE);
		int s = calendar.get(Calendar.SECOND);
		
		return (double)h + (double)m / 60 + (double)s / 3600;		
	}
	
	public double getHoraDoubleComFuso(){
		return this.getHoraDouble() + (this.getFuso()*-1);
	}
	
	public Coordenada getLatitude() {
		return latitude;
	}
	/*
	public void setLatitude(Coordenada latitude) {
		this.latitude = latitude;
	}
	*/
	public Coordenada getLongitude() {
		return longitude;
	}
	/*
	public void setLongitude(Coordenada longitude) {
		this.longitude = longitude;
	}
	*/
	public String getData() {
		return data;
	}
	/*
	public void setData(String data) {
		this.data = data;
	}
	*/
	public String getHora() {
		return hora;
	}/*
	public void setHora(String hora) {
		this.hora = hora;
	}
	*/
	public int getFuso() {
		return fuso;
	}
	/*
	public void setFuso(int fuso) {
		this.fuso = fuso;
	}
	 */
	public int getAno(){
		return calendar.get(Calendar.YEAR);
	}
	
	public int getMes(){
		return calendar.get(Calendar.MONTH) + 1;
	}
	
	public int getDia(){
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	public int getMinute(){
		return calendar.get(Calendar.MINUTE);
	}
	
	public int getSegundo(){
		return calendar.get(Calendar.SECOND);
	}
	
	/*
	public CabecalhoMapa getCabecalhoMapa(){
		return this.cabecalho;
	}
	*/
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
