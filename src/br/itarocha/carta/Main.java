package br.itarocha.carta;

public class Main {

	//http://th-mack.de/international/download/index.html
	//http://www.radixpro.org/fix-east-west.html
	public static void main( String[] args) {
		ConstrutorMapa construtor = new ConstrutorMapa();
		Mapa mapaItamar 	= construtor.buildMapa("1972.6.29","5.0.0",-3,"-4.51.32","-43.21.22");
		Mapa mapaSaoPaulo	= construtor.buildMapa("2014.10.8","17.0.0",-3,"-23.32.51","-46.38.10");
		Mapa mapaLivia		= construtor.buildMapa("2001.9.28","7.0.0",-3,"-18.55.7","-48.16.38");
		mapaLivia.setNome("Lívia");

		construtor.display(mapaLivia);
		System.out.println("\n**********************************************\n");
		construtor.display(mapaItamar);
		System.out.println("\n**********************************************\n");
		construtor.display(mapaSaoPaulo);
	}
}
/*
Tempo Sideral 23:36:35
SOL 07 Ca 39.47
LUA 07 Aq 27.55
MER 00 Le 56.03
VEN 20 Ge 08.45 R
MAR 00 Le 25.07
JUP 02 Cp 52.35 R
SAT 13 Ge 39.17
URA 14 Li 13.08
NET 03 Sg 00.16 R
PLU 29 Vg 25.17
*/

// Site Teoria da Conspiração - Link de mapa
// http://www.deldebbio.com.br/
// http://www.viraj.com.br/
// http://www.sadhana.com.br/cgi-local/mapas/mapanow.cgi


/*	
SP - 19/09/2014 - 12:16 UTC (9:16)
ver mapa
Sol 26° Vg 29
Lua 04° Le 30
Mer 22° Li 39
Vên 17° Vg 06
Mar 03° Sg 46
Júp 13° Le 51
Sat 19° Es 27
Ura 15° Ár 14 R
Net 05° Pe 38 R
Plu 10° Cp 59 R
Nod 20° Li 25 R
Lil 22° Le 15
Qui 14° Pe 52 R
*/