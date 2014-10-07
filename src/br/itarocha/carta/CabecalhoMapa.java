package br.itarocha.carta;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CabecalhoMapa {
	
	private Calendar calendar;
	private String nome;
	private String nomeCidade;
	private Coordenada latitude;
	private Coordenada longitude;
	private String data; // 
	private String hora; //
	private int fuso;
	
	
	public CabecalhoMapa(int ano, int mes, int dia, int hora, int minuto, int segundo, int fuso){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		// Mes base 0 ZEROOOOOO. Janeiro = 0; Fevereiro = 1
		this.calendar = new GregorianCalendar(ano,mes-1,dia,hora,minuto,segundo);
		
		this.fuso = fuso;
		
		//System.out.println(sdf.format(calendar.getTime()));
		//int _mes = calendar.get(Calendar.MONTH)+1;
		//System.out.println("MES = "+mes);

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
	public Coordenada getLatitude() {
		return latitude;
	}
	public void setLatitude(Coordenada latitude) {
		this.latitude = latitude;
	}
	public Coordenada getLongitude() {
		return longitude;
	}
	public void setLongitude(Coordenada longitude) {
		this.longitude = longitude;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getFuso() {
		return fuso;
	}
	public void setFuso(int fuso) {
		this.fuso = fuso;
	}

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
	
}
