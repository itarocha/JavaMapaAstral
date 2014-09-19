package br.itarocha.carta.main;

import java.util.ArrayList;
import java.util.List;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;

public class Mapa {
	
	private CabecalhoMapa cabecalho;
	private List<PlanetaPosicao> posicoesPlanetas = new ArrayList<PlanetaPosicao>();
	private List<ItemAspecto> la = new ArrayList<ItemAspecto>();
	private List<Cuspide> cuspides = new ArrayList<Cuspide>();

	private SweDate sweDate; 

	private static final int SID_METHOD = SweConst.SE_SIDM_LAHIRI; // .SE_SIDM_LAHIRI;
	
	private static final String[] signos = {"Ar","To","Ge","Ca","Le","Vi","Li","Es","Sg","Cp","Aq","Pe"};
	private static final String[] planetasAspectos = {"Sol","Lua","Mer","Ven","Mar","Jup","Sat","Ura","Net","Plu","tNd", "As ", "MC "};
	
	public Mapa(CabecalhoMapa cab){
		this.cabecalho = cab;
		buildMapa();
	}
	
	public List<PlanetaPosicao> GetPosicoesPlanetas(){
		return this.posicoesPlanetas;
	}
	
	public List<ItemAspecto> GetListaAspectos(){
		return this.la;
	}
	
	public List<Cuspide> GetListaCuspides(){
		return this.cuspides;
	}

	
	public void display(){
		// TODO: REGIÃO DE DISPLAY

		System.out.println("\nPLANETAS");
		for(PlanetaPosicao pp : posicoesPlanetas){
			System.out.print(
				  String.format("%-12s",pp.getNomePlaneta())+"\t"+ 	// Planeta
				  CartaUtil.grau(pp.getPosicao())+" "+				// Longitude  
				   (pp.isRetrogrado() ? 'R' : 'D')+" "+ 			// Retrogrado ou Direto?
				  String.format("%10.7f",pp.getLatitude())+"\t"+	// Latitude
				  String.format("%10.7f",pp.getDistancia())+"\t"+   // Distância
				  String.format("%10.7f",pp.getDirecao())+"\t"+   	// Direção
				  CartaUtil.grauNaCasa(pp.getPosicao())+			// Grau na Casa 
				  pp.getNomeSigno()+"\n" // Signo					// Nome do Signo
				  );
		}
		
		// TODO: REGIÃO DE DISPLAY (ISOLAR!!!)
		System.out.println("\nCÚSPIDES");
		System.out.println("\nCasa 1 = Ascendente\nCasa 10 = MC\n");
		for (Cuspide c: cuspides){
			System.out.println(String.format("casa %2d %s %s %s", 
					c.getNumero(), CartaUtil.grau(c.getPosicao()), CartaUtil.grauNaCasa(c.getPosicao()), c.getSigno()));			
		}

		// TODO: REGIÃO DE DISPLAY (ISOLAR!!!)
		System.out.println("\nASPECTOS");
		for(ItemAspecto ite : la){
			System.out.println(String.format("%s %s %s (%s e %s) [%02d x %02d] = %s", 
					planetasAspectos[ite.getPlanetaA().getCoordenada()], ite.getAspecto(), planetasAspectos[ite.getPlanetaB().getCoordenada()],
					CartaUtil.grau(ite.getPlanetaA().getPosicao()), CartaUtil.grau(ite.getPlanetaB().getPosicao()),
					ite.getPlanetaA().getCoordenada(), ite.getPlanetaB().getCoordenada(), ite.getAspecto() ));
		}
		
	}
	
	// TODO: Deve retornar uma classe Mapa
	private void buildMapa(){
		//http://www.timeanddate.com/worldclock/switzerland/zurich
		//http://www.sadhana.com.br/cgi-local/mapas/mapanow.cgi?indic=10003&ref=http%3A//www.deldebbio.com.br/
			
		int sign;
		int house;
		boolean retrograde = false;
		String signoName = "";
		// ITAMAR
		int[] aspectos_planetas = new int[18];
		double[] aspectos_posicoes = new double[18];
		int idxpos = -1;
		
		double hour = cabecalho.getHoraDoubleComFuso(); //cab.getHoraDouble();
		//System.out.println(hour);

		double latitude = cabecalho.getLatitude().Coordinates2Degrees(); // Conversions.Coordinates2Degrees("4.51.32","-");
		double longitude = cabecalho.getLongitude().Coordinates2Degrees(); //Conversions.Coordinates2Degrees("43.21.22","-");
		
		/*Instances of utility classes */
		SwissEph sw = new SwissEph();
		//SweDate sd = 
				
		sweDate = new SweDate(cabecalho.getAno(),cabecalho.getMes(),cabecalho.getDia(),hour);
		sweDate.setCalendarType(sweDate.SE_GREG_CAL,sweDate.SE_KEEP_DATE);

		// Set sidereal mode:
		///////////////////////////sw.swe_set_sid_mode(SID_METHOD, 0, 0);

		// BEGIN DESVIO!!!!
		double tjd, te;
		tjd=sweDate.getJulDay();
		//te = tjd;
		te = tjd + sweDate.getDeltaT(tjd);
		long iflag, iflgret;
		double x2[]=new double[6];
		StringBuffer serr=new StringBuffer();
		String /*sdate="", */snam;
		snam=null; // Realistisch?
		iflag = SweConst.SEFLG_SPEED;
		
		// Some required variables:
		//double[] cusps = new double[13];
		//double[] acsc = new double[10];
		double[] xp= new double[6];
		double[] casas= new double[23];

		casas = getHouses(sw, sweDate.getJulDay(), latitude, longitude);
		

		// http://www.astrosage.com/astrology/ayanamsa-calculator.asp
		// Get and print ayanamsa value for info:
		double ayanamsa = sw.swe_get_ayanamsa_ut(sweDate.getJulDay());
		int signoAscendente = (int)(casas[1] / 30)+1;
		//int ascSign = (int)(casas[1] / 30) + 1;

		
		// Detalhes de entrada:
		System.out.println("Data: " + sweDate);
		System.out.println("Localização: " +
				CartaUtil.grau(longitude) + (longitude > 0 ? "E" : "W") +
				" / " +
				CartaUtil.grau(latitude) + (latitude > 0 ? "N" : "S"));
		System.out.println("Ayanamsa: " + CartaUtil.grau(ayanamsa) + " (" + sw.swe_get_ayanamsa_name(SID_METHOD) + ")");
		System.out.println("Ascendente: " + CartaUtil.grauNaCasa(casas[1])+" "+signos[signoAscendente-1]);
		
		//SweConst.SE_MEAN_APOG 
		//SweConst.SE_OSCU_APOG
		//SweConst.SE_MEAN_NODE
		//SweConst.SE_CHIRON
	
		
		// O último era SE_CHIRON
		for (int p = SweConst.SE_SUN; p <= SweConst.SE_TRUE_NODE; p++) {
			if ((p == SweConst.SE_EARTH) || (p == SweConst.SE_MEAN_NODE) ) continue;

			//SweConst.SE_MEAN_APOG 
			//SweConst.SE_OSCU_APOG
			//SweConst.SE_MEAN_NODE
			//SweConst.SE_CHIRON
			
		   	//Do the coordinate calculation for this planet p
		  
			// x2[0] = longitude (Planeta)
			// x2[1] = latitude
			// x2[2] = distância
			// x2[3] = velocidade do planeta em longitude // Se negativo, retrógrado
			// x2[4] = velodicade em latitude
			// x2[5] = velocidade em distância???
			
			
			iflgret = sw.swe_calc(te, p, (int)iflag, x2, serr);
			// if there is a problem, a negative value is returned and an errpr message is in serr.
			if (iflgret < 0)
				System.out.print("error: "+serr.toString()+"\n");
			else if (iflgret != iflag)
				System.out.print("warning: iflgret != iflag. "+serr.toString()+"\n");
		  
			//get the name of the planet p
		  
			snam=sw.swe_get_planet_name(p);
			
			//print the coordinates
		  
			sign = (int)(x2[0] / 30) + 1;
			signoName = signos[sign-1]; 
			house = (sign + 12 - signoAscendente) % 12 +1;
			retrograde = (x2[3] < 0);
		  
			// Atualizando posições para cálculo de aspectos
			idxpos++;
			aspectos_planetas[idxpos] = p; 			
			aspectos_posicoes[idxpos] = x2[0]; 			
			
			PlanetaPosicao pp = new PlanetaPosicao();
			
			pp.setNomePlaneta(snam);
			pp.setNomeSigno(signoName);
			pp.setPosicao(x2[0]);
			pp.setRetrogrado(retrograde);
			pp.setLatitude(x2[1]);
			pp.setDistancia(x2[2]);
			pp.setDirecao(x2[3]);
			
			posicoesPlanetas.add(pp);
		}
		
		// Ascendente e Meio do Céu
		aspectos_planetas[11] = SweConst.SE_ASC;
		aspectos_posicoes[11] = casas[1];

		aspectos_posicoes[12] = casas[10];
		aspectos_planetas[12] = SweConst.SE_MC;

		// Fabricando Cúspides
		for (int i = 1; i < 21; i++){
			sign = (int)(casas[i] / 30) + 1;
			signoName = signos[sign-1]; 
			
			Cuspide cuspide = new Cuspide();
			cuspide.setNumero(i);
			cuspide.setPosicao(casas[i]);
			cuspide.setSigno(signoName);
			
			cuspides.add(cuspide);
		}
		
		// Fabricando de Aspectos
		String aspecto;
		for (int x=0; x < 12; x++){
			for(int y=x+1; y < 13; y++){
				aspecto = CartaUtil.buildAspect(aspectos_posicoes[x], aspectos_posicoes[y]);
				if (aspecto != ""){
					ItemAspecto item = new ItemAspecto();
					item.getPlanetaA().setPlaneta(aspectos_planetas[x]);
					item.getPlanetaA().setPosicao(aspectos_posicoes[x]);

					item.getPlanetaB().setPlaneta(aspectos_planetas[y]);
					item.getPlanetaB().setPosicao(aspectos_posicoes[y]);
					
					item.setAspecto(aspecto);
					item.getPlanetaA().setCoordenada(x);
					item.getPlanetaB().setCoordenada(y);

					la.add(item);
				}
			}
		} // end aspecto	
		
	}
	
    /// <summary>
    /// Calculate houses
    /// </summary>
    /// <param name="jdnr">Julian day number</param>
    /// <param name="lat">Geographical latitude</param>
    /// <param name="lon">Geographical longitude</param>
    /// <param name="system">Index to define housesystem</param>
    /// <returns>Array of doubles with with the following values:
    ///  0: not used, 1..12 cusps 1..12, 13: asc., 14: MC, 15: ARMC, 16: Vertex,
    ///  17: Equatorial asc., 18: co-ascendant (Koch), 19: co-ascendant(Munkasey),
    ///  20: polar ascendant 
    ///</returns>
    private static double[] getHouses(SwissEph sw, double jdnr, double lat, double lon) {
        double[] xx = new double[13];
        double[] yy = new double[10];
        double[] zz = new double[23];
        int flag = sw.swe_houses(jdnr, SweConst.SEFLG_SPEED, lat, lon, 'P', xx, yy);
        
        for (int i = 0; i < 13; i++) {
            zz[i] = xx[i];
        }
        for (int i = 0; i < 10; i++) {
            zz[i + 13] = yy[i];
        }
        return zz;
    }
	
}
