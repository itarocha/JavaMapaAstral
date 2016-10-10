package br.itarocha.carta;

public class Cidade {
	private int codigo;
	private String nomeOriginal;
	private String nomeSemAcento;
	private String uf;
	private String latitude;
	private String longitude;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNomeOriginal() {
		return nomeOriginal;
	}
	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}
	public String getNomeSemAcento() {
		return nomeSemAcento;
	}
	public void setNomeSemAcento(String nomeSemAcento) {
		this.nomeSemAcento = nomeSemAcento;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getUF() {
		return uf;
	}
	public void setUF(String uf) {
		this.uf = uf;
	}
}
