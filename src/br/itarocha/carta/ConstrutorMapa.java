package br.itarocha.carta;

import java.util.ArrayList;
import java.util.List;

import swisseph.SweConst;
import swisseph.SweDate;
import swisseph.SwissEph;


public class ConstrutorMapa {
	private SweDate sweDate; 

	private int[] aspectos_planetas = new int[18];
	private double[] aspectos_posicoes = new double[18];
	private double[] casas= new double[23];
	private SwissEph sw;
	private double ayanamsa;

	private static final int SID_METHOD = SweConst.SE_SIDM_LAHIRI;
	
	private static final String[] signos = {"ar","to","ge","ca","le","vi","li","es","sg","cp","aq","pe"};
	private List <Planeta> mapPlanetas = new ArrayList<Planeta>();
	
	private void preparar(){
		mapPlanetas.add(new Planeta(SweConst.SE_SUN, "sol", "Sol"));
		mapPlanetas.add(new Planeta(SweConst.SE_MOON, "lua", "Lua"));
		mapPlanetas.add(new Planeta(SweConst.SE_MERCURY, "mer", "Mercúrio"));
		mapPlanetas.add(new Planeta(SweConst.SE_VENUS, "ven", "Vênus"));
		mapPlanetas.add(new Planeta(SweConst.SE_MARS, "mar", "Marte"));
		mapPlanetas.add(new Planeta(SweConst.SE_JUPITER, "jup", "Júpiter")); 
		mapPlanetas.add(new Planeta(SweConst.SE_SATURN, "sat", "Saturno"));
		mapPlanetas.add(new Planeta(SweConst.SE_URANUS, "ura", "Urano")); 
		mapPlanetas.add(new Planeta(SweConst.SE_NEPTUNE, "net", "Netuno")); 
		mapPlanetas.add(new Planeta(SweConst.SE_PLUTO, "plu", "Plutão"));

		mapPlanetas.add(new Planeta(SweConst.SE_TRUE_NODE, "tnd", "True Node"));
		
		mapPlanetas.add(new Planeta(SweConst.SE_ASC, "asc", "Ascendente"));
		mapPlanetas.add(new Planeta(SweConst.SE_MC, "mce", "Meio do Céu"));

		// RETIREI ESSE NEGÓCIO DE NODO NORTE
		
	}
	
	public ConstrutorMapa(){
		this.sw = new SwissEph();
		preparar();
	}
	
	public Mapa buildMapa(String nome, String data, String hora, int fuso, String lat, String lon){
		Mapa mapa = new Mapa(nome, data, hora, fuso, lat, lon);
		buildMapa(mapa);
		//System.out.println("Ayanamsa: " + ayanamsa);
		//System.out.println("Ayanamsa: " + CartaUtil.grau(ayanamsa) + " (" + sw.swe_get_ayanamsa_name(SID_METHOD) + ")");
		
		return mapa;
	}

	// TODO: Deve retornar uma classe Mapa
	private void buildMapa(Mapa mapa){
		//http://www.timeanddate.com/worldclock/switzerland/zurich
		//http://www.sadhana.com.br/cgi-local/mapas/mapanow.cgi?indic=10003&ref=http%3A//www.deldebbio.com.br/
		// http://www.astrosage.com/astrology/ayanamsa-calculator.asp
		// Get and print ayanamsa value for info:
		this.sweDate = new SweDate(	
				mapa.getAno(),
				mapa.getMes(),
				mapa.getDia(),
				mapa.getHoraDoubleComFuso());
		
		this.sweDate.setCalendarType(this.sweDate.SE_GREG_CAL, this.sweDate.SE_KEEP_DATE);
		this.ayanamsa = this.sw.swe_get_ayanamsa_ut(this.sweDate.getJulDay());

		buildCasas(mapa);
		buildPlanetas(mapa);
		buildAspectos(mapa);
	}

	// Do the coordinate calculation for this planet p
	// x2[0] = longitude (Planeta)
	// x2[1] = latitude
	// x2[2] = distância
	// x2[3] = velocidade do planeta em longitude // Se negativo, retrógrado
	// x2[4] = velodicade em latitude
	// x2[5] = velocidade em distância???
	private void buildPlanetas(Mapa mapa){
		int signo;
		//String nomeSigno = "";
		long iflag, iflgret;
		iflag = SweConst.SEFLG_SPEED;

		double tjd, te;
		tjd=sweDate.getJulDay();
		te = tjd + sweDate.getDeltaT(tjd);
		double x[]=new double[6];
		double x2[]=new double[6];
		StringBuffer serr=new StringBuffer();
		boolean retrogrado = false;
		int idxpos = -1;

		mapa.getPosicoesPlanetas().clear();
		
		iflgret = sw.swe_calc(te, SweConst.SE_ECL_NUT, (int)iflag, x, serr);
		
		
		// O último era SE_CHIRON
		for(int xis = 0; xis <= 10; xis++){
			Planeta planeta = mapPlanetas.get(xis);
			
			iflgret = sw.swe_calc(te, planeta.getId(), (int)iflag, x2, serr);
			// if there is a problem, a negative value is returned and an errpr message is in serr.
			if (iflgret < 0)
				System.out.print("error: "+serr.toString()+"\n");
			else if (iflgret != iflag)
				System.out.print("warning: iflgret != iflag. "+serr.toString()+"\n");
		  
			//print the coordinates
			signo = (int)(x2[0] / 30) + 1;
			//house = (sign + 12 - signoAscendente) % 12 +1;
			retrogrado = (x2[3] < 0);
		  
			// Atualizando posições para cálculo de aspectos
			idxpos++;
			aspectos_planetas[idxpos] = planeta.getId();
			aspectos_posicoes[idxpos] = x2[0]; 			
			
			PlanetaPosicao pp = new PlanetaPosicao();
			
			pp.setNomePlaneta(planeta.getNome());
			pp.setSiglaPlaneta(planeta.getSigla());
			pp.setNomeSigno(signos[signo-1]);
			pp.setPosicao(x2[0]);
			pp.setGrau( CartaUtil.grau(x2[0]) );
			pp.setGrauNaCasa( CartaUtil.grauNaCasa(x2[0]) );
			pp.setRetrogrado(retrogrado);
			pp.setLatitude(x2[1]);
			pp.setDistancia(x2[2]);
			pp.setDirecao(x2[3]);
			
			
			
			//swisseph.SwissEph.sw
			//SwissEph.swe_
			
            //double hpos = swe_house_pos(armc, lat, eps_true, hsys, x, ref serr);
            //double hpos = swe_house_pos(0d, 0d, 0d, 0, 0, 0 0);
			
	        //double hpos = sw.swe_houses(jdnr, SweConst.SEFLG_SPEED, lat, lon, 'P', xx, yy);
			// 

			
			/*
			sw.swe_sid
			
            double sidt = sw.swe_sidtime(tjd) + lon / 15;
            if (sidt >= 24)
                sidt -= 24;
            if (sidt < 0)
                sidt += 24;
            armc = sidt * 15;
            */
			
			
			double _geolat = mapa.getLatitude().Coordenada2Graus(); // ok
			double _armc = mapa.getSideralTime(); // ok
			double _eps_true = x[0];
			//System.out.println("Latitude.... "+_geolat);
			//System.out.println("Sideral Time.... "+_armc);
			//System.out.println("EPS TRUE.... "+_eps_true);
			
			
			
			
	        double hpos = sw.swe_house_pos(_armc, _geolat, _eps_true, 'P', x2, serr);
			
			
			
			pp.setCasa(hpos);
			
			
			mapa.getPosicoesPlanetas().add(pp);
		}
		
		// Ascendente e Meio do Céu
		aspectos_planetas[11] = SweConst.SE_ASC;
		aspectos_posicoes[11] = casas[1];

		aspectos_posicoes[12] = casas[10];
		aspectos_planetas[12] = SweConst.SE_MC;
	}
	
	// Fabricando Cúspides
	// Depende do cálculo das casas
	private void buildCasas(Mapa mapa){
		int sign;
		mapa.getListaCuspides().clear();
		
		this.casas = this.getHouses(this.sw, 
				mapa, 
				sweDate.getJulDay(), 
				mapa.getLatitude().Coordenada2Graus(),
				mapa.getLongitude().Coordenada2Graus() );
		
		for (int i = 1; i < 21; i++){
			sign = (int)(casas[i] / 30) + 1;
			
			Cuspide cuspide = new Cuspide();
			cuspide.setNumero(i);
			cuspide.setPosicao(casas[i]);
			cuspide.setGrau( CartaUtil.grau(casas[i]));
			cuspide.setGrauNaCasa( CartaUtil.grauNaCasa(casas[i]) );
			cuspide.setSigno(signos[sign-1]);
			
			mapa.getListaCuspides().add(cuspide);
		}
	}
	
	// Fabricando de Aspectos
	private void buildAspectos(Mapa mapa){
		mapa.getListaAspectos().clear();
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

					item.getPlanetaA().setSigla(mapPlanetas.get(x).getSigla());
					item.getPlanetaB().setSigla(mapPlanetas.get(y).getSigla());

					item.getPlanetaA().setGrau(CartaUtil.grau(aspectos_posicoes[x]));
					item.getPlanetaB().setGrau(CartaUtil.grau(aspectos_posicoes[y]));
					
					mapa.getListaAspectos().add(item);
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
	///
	///  yy[0] = ascendant
	///  yy[1] = mc
	///  yy[2] = armc (= sidereal time) !!!!!
	///  yy[3] = vertex
	///  yy[4] = equatorial ascendant
	///  yy[5] = co-ascendant (Walter Koch)
	///  yy[6] = co-ascendant (Michael Munkasey)
	///  yy[7] = polar ascendant (Michael Munkasey)
	///  yy[8] = reserved for future use
	///  yy[9] = reserved for future use
    ///</returns>
    private double[] getHouses(SwissEph sw, Mapa mapa, double jdnr, double lat, double lon) {
        double[] xx = new double[13];
        double[] yy = new double[10];
        double[] zz = new double[23];
        int flag = sw.swe_houses(jdnr, SweConst.SEFLG_SPEED, lat, lon, 'P', xx, yy);
        
        mapa.setSideralTime(yy[2]);
        
        for (int i = 0; i < 13; i++) {
            zz[i] = xx[i];
        }
        for (int i = 0; i < 10; i++) {
            zz[i + 13] = yy[i];
        }
        return zz;
    }
	
    
    
}

//SweConst.SE_MEAN_APOG 
//SweConst.SE_OSCU_APOG
//SweConst.SE_MEAN_NODE
//SweConst.SE_CHIRON
//get the name of the planet p
//snam=sw.swe_get_planet_name(p);



//%-12s %10.7f\t %10.7f\t %10.7f\t
//pp.getSiglaPlaneta()
//pp.getLatitude(),			// Latitude
//pp.getDistancia(),   		// Distância
//pp.getDirecao()   		// Direção