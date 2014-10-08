package br.itarocha.carta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import swisseph.*;


/**
 * This class is an example of how to calculate
 * planets and houses in the indian vedic style
 * of astrology (jyotish).
*/
public class Main {

	/**
	* The method to determine ayanamsha value:
	*/
	//private static final int SID_METHOD = SweConst.SE_EARTH;// .SE_SIDM_LAHIRI;
	//private static final int SID_METHOD = SweConst.SE_SIDM_LAHIRI; // .SE_SIDM_LAHIRI;
	
	//private static final String[] signos = {" Ar "," To "," Ge "," Ca "," Le "," Vi "," Li "," Es "," Sg "," Cp "," Aq "," Pe "};
	//private static final String[] planetasAspectos = {"Sol","Lua","Mer","Ven","Mar","Jup","Sat","Ura","Net","Plu","tNd", "As ", "MC "};

	
	//http://th-mack.de/international/download/index.html
	//http://www.radixpro.org/fix-east-west.html
	public static void main( String[] args) {
		
		/*
		//Uberlândia
		//double longitude = Conversions.Coordinates2Degrees("18.55.07","-");
		//double latitude = Conversions.Coordinates2Degrees("48.16.38","-");
		*/
		
		// Hora sideral: 14:52:57
		// CabecalhoMapa(2014,9,17,15,00,00,-3);
		//cabIta.setLatitude(new Coordenada(4,51,32,"-"));
		//cabIta.setLongitude(new Coordenada(43,21,22,"-"));
		
		
		//CabecalhoMapa cabIta = new CabecalhoMapa(1972,6,29,5,00,00,-3);
		/*
		CabecalhoMapa cabIta = new CabecalhoMapa(2014,9,17,15,00,00,-3);
		cabIta.setNome("Itamar Rocha");
		cabIta.setLatitude(new Coordenada(4,51,32,"-"));
		cabIta.setLongitude(new Coordenada(43,21,22,"-"));

		Mapa mapaItamar = new Mapa(cabIta);
		mapaItamar.display();
		*/
		
		
		//System.out.println("\n**********************************************\n");
		//CabecalhoMapa cabsp = new CabecalhoMapa("1972.6.29","5.0.0",-3,"-18.55.7","-48.16.38");
		
		
		//CabecalhoMapa cabsp = new CabecalhoMapa(2001,9,28,7,0,0,-3);
		//cabsp.setLatitude(new Coordenada(23,32,51,"-"));
		//cabsp.setLongitude(new Coordenada(46,38,10,"-"));
		//double longitude = Conversions.Coordinates2Degrees("18.55.07","-");
		//double latitude = Conversions.Coordinates2Degrees("48.16.38","-");
		
		//cabsp.setLatitude(new Coordenada(18,55,7,"-"));
		//cabsp.setLongitude(new Coordenada(48,16,38,"-"));
		ConstrutorMapa construtor = new ConstrutorMapa();
		
		Mapa mapa = construtor.buildMapa("1972.6.29","5.0.0",-3,"-18.55.7","-48.16.38");
		mapa.setNome("Lívia");
		construtor.display(mapa);
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