package br.itarocha.carta;

public class DecoradorMapa {

	public DecoradorMapa(){}
	
	public String getJSON(Mapa mapa){
		String retorno = "";
		
		retorno += displayCabecalho(mapa);
		retorno += ",\n";
		retorno += displayPlanetasNosSignos(mapa);
		retorno += ",\n";
		retorno += displayPlanetasNasCasas(mapa);
		retorno += ",\n";
		retorno += displayCuspides(mapa);
		retorno += ",\n";
		retorno += displayAspectos(mapa);
		
		retorno = "{"+retorno+"}";
		
		return retorno;
	}
    
    private String displayCabecalho(Mapa mapa){
    	double latitude = mapa.getLatitude().Coordenada2Graus();
    	double longitude = mapa.getLongitude().Coordenada2Graus();	
    	////////int signoAscendente = (int)(casas[1] / 30)+1;
    	String retorno = "";
    	
    	String lon = (longitude > 0 ? "E" : "W");
    	String lat = (latitude > 0 ? "N" : "S");
    	
    	retorno += String.format("\"dados_pessoais\": {\"nome\": \"%s\", "+
    								"\"data\":\"%s\", "+
    								"\"hora\":\"%s\", "+
    								"\"lat\":\"%s\", "+
    								"\"lon\":\"%s\"}",
    							mapa.getNome(),
    							mapa.getData(),
    							mapa.getHora(),
    							CartaUtil.grau(latitude)+lat,
    							CartaUtil.grau(longitude)+lon
    			);
    	
		return retorno;
		
		// VEJA MAIS TARDE
		//System.out.println("Ayanamsa: " + CartaUtil.grau(ayanamsa) + " (" + sw.swe_get_ayanamsa_name(SID_METHOD) + ")");
		///////System.out.println("Ascendente: " + CartaUtil.grauNaCasa(casas[1])+" "+signos[signoAscendente-1]);
    }
	
    
    /*
    {"planetas_signos":[
      {"planeta":"sol", "signo":"ca", "gg":"07", "mm":"39"},
      {"planeta":"lua", "signo":"aq", "gg":"07", "mm":"27"},
      {"planeta":"mer", "signo":"le", "gg":"00", "mm":"56"},
      {"planeta":"ven", "signo":"ge", "gg":"20", "mm":"08"},
      {"planeta":"mar", "signo":"le", "gg":"00", "mm":"25"},
      {"planeta":"jup", "signo":"cp", "gg":"02", "mm":"52"},
      {"planeta":"sat", "signo":"ge", "gg":"13", "mm":"39"},
      {"planeta":"ura", "signo":"li", "gg":"14", "mm":"13"},
      {"planeta":"net", "signo":"sg", "gg":"03", "mm":"00"},
      {"planeta":"plu", "signo":"vi", "gg":"29", "mm":"25"}
    ]}
    */
	private String displayPlanetasNosSignos(Mapa mapa){
		String retorno = "";
		
		//System.out.println("\nPLANETAS");
		for(PlanetaPosicao pp : mapa.getPosicoesPlanetas()){
			String gnc = pp.getGrauNaCasa();
			gnc = gnc.replace('.', '-');
			String[] gms = gnc.split("-");
			
			retorno += String.format("{\"planeta\":\"%s\", \"signo\":\"%s\", \"gg\":\"%s\", \"mm\":\"%s\", \"ss\":\"%s\"},\n",
					pp.getSiglaPlaneta(), 	// Planeta
					pp.getNomeSigno(),		// Signo
					gms[0],					// gg 
					gms[1],					// mm 
					gms[2]					// ss 
					);
		}
		retorno =  "\"planetas_signos\":[\n"+
					retorno.substring(0,retorno.length()-2)+
					"\n]";
		return retorno;
	}

	private String displayPlanetasNasCasas(Mapa mapa){
		String retorno = "";
		
		for(PlanetaPosicao pp : mapa.getPosicoesPlanetas()){
			retorno += String.format("{\"planeta_casa\":\"%s\", \"casa\":\"%d\", \"posicao\":\"%s\"},\n",
					pp.getSiglaPlaneta(), 	// Planeta
					(int)pp.getCasa(),		// Casa (decimal)
					pp.getCasa()		   // Casa (double)
					);
		}
		retorno =  "\"planetas_casas\":[\n"+
				  retorno.substring(0,retorno.length()-2)+
				  "\n]";
		return retorno;
	}

	private String displayCuspides(Mapa mapa){ 
		String retorno = "";
		for (Cuspide c: mapa.getListaCuspides() ){
			if (c.getNumero() > 12) { break; }
			
			String gnc = c.getGrauNaCasa();
			gnc = gnc.replace('.', '-');
			String[] gms = gnc.split("-");
			
			retorno += String.format("{\"casa\":\"%d\", \"signo\":\"%s\", \"gg\":\"%s\", \"mm\":\"%s\", \"ss\":\"%s\"},\n",
					c.getNumero(), 	// Casa
					c.getSigno(),		// Signo
					gms[0],					// gg 
					gms[1],					// mm 
					gms[2]					// ss 
					);
		}
		retorno =  "\"cuspides\":[\n"+
				  retorno.substring(0,retorno.length()-2)+
				  "\n]";
		return retorno;
	}

	private String displayAspectos(Mapa mapa){
		String retorno = "";
		for(ItemAspecto ite : mapa.getListaAspectos()){
			PlanetaAspecto pA = ite.getPlanetaA();
			PlanetaAspecto pB = ite.getPlanetaB();
			
			retorno += String.format("{\"planeta_origem\":\"%s\", \"planeta_destino\":\"%s\", \"aspecto\":\"%s\"},\n",
					pA.getSigla(), 
					pB.getSigla(),
					ite.getAspecto()
					);
		}
		retorno = "\"aspectos\":[\n"+
				  retorno.substring(0,retorno.length()-2)+
				  "\n]";
		return retorno;
	}
	
}
