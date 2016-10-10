package br.itarocha.carta;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.Normalizer;
import java.util.Properties;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 
	
public class MapeadorCidades {
	
	//private Map<Class<?>,Class<?>> mapa = new HashMap<Class<?>,Class<?>>();
	
	private List<Cidade> listaCidades = new ArrayList<Cidade>();
	

	/*
	public void load(String nomeArquivo) throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream(nomeArquivo));
		
		for (Object k : prop.keySet()){
			System.out.println(k.toString());
			
			Class<?> itf = Class.forName(k.toString());
			Class<?> impl = Class.forName(prop.get(k).toString());
			
			if (!itf.isInterface()){
				throw new RuntimeException(k.toString() + " não é uma interface");
			}
			if (!itf.isAssignableFrom(impl)){
				throw new RuntimeException("A classe "+impl.getName()+" não implementa "+itf.getName());
			}
			
			mapa.put(itf, impl);
		}
		
	}
	*/
	
	public MapeadorCidades(){
		this.carregarArquivoCidades();
	}
	
	private void carregarArquivoCidades(){
		
	    //String arquivoCSV = ".//ephe//cidades_brasil.csv ";
	    String arquivoCSV = "ephe/cidades_brasil.csv";
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ",";
	    try {

	        br = new BufferedReader(new FileReader(arquivoCSV));
	        int i = -1;
	        while (  ((linha = br.readLine()) != null) ) {
	        	i++;

	        	if (i > 0) {

		    		/*
		    		Ler o arquivo //ephe//cidades_brasil.csv 
		    		"codigo","lat","e/w","lon","n/s","nome sem acento","uf","nome original","altitude","area"
		    		1,"-16.45.26","W","-49.26.15","S","abadia de goias","GO","Abadia de Goiás",898.000,136.900
		    		2,"-18.29.08","W","-47.24.11","S","abadia dos dourados","MG","Abadia dos Dourados",742.000,897.400
		    		3,"-16.12.15","W","-48.42.25","S","abadiania","GO","Abadiânia",1052.000,1047.700
		    		*/
		            String[] cidade = linha.split(csvDivisor);
		            
		            Cidade c = new Cidade();
		            /*
		            int codigo = 0;
		            codigo = Integer.parseInt(cidade[0]);
		            */
		            
		            c.setCodigo(Integer.parseInt(cidade[0]));
		            c.setNomeSemAcento(cidade[5].replaceAll("\"", ""));
		            c.setUF(cidade[6].replaceAll("\"", ""));
		            c.setNomeOriginal(cidade[7].replaceAll("\"", ""));
		            c.setLatitude(cidade[1].replaceAll("\"", ""));
		            c.setLongitude(cidade[3].replaceAll("\"", ""));
		            
		            this.listaCidades.add(c);
		            //System.out.println("Contem "+this.listaCidades.size());
		            
		            /*
		            System.out.println(String.format("%d,[%s],%s,%s,%s,%s",c.getCodigo(), 
		            		c.getNomeSemAcento(), c.getNomeOriginal(), c.getUF(), c.getLatitude(), c.getLongitude()));
		            */		
		            		
		            
	        	}

	            /*
	            System.out.println("País [code= " + pais[pais.length-2] 
	                                 + " , name=" + pais[pais.length-1] + "]");
				*/
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		
	}
	
	public Cidade getCidade(String nome, String uf){
		System.out.println("Verificando cidades...");
		Cidade retorno = new Cidade();
		String nomeSemAcento = removerAcentos(nome).toLowerCase();
		uf = uf.toLowerCase();
		System.out.println("Contem "+this.listaCidades.size());
		
		System.out.println("Verificando "+nomeSemAcento);
		
		for (Cidade c : this.listaCidades){
			//System.out.println("Verificando se "+c.getNomeSemAcento()+" é igual a "+nomeSemAcento);
			
			if ( c.getNomeSemAcento().equals(nomeSemAcento) &&
				 c.getUF().toLowerCase().equals(uf)	
			   ) {
				retorno = c;
				break;
			}
		}
		return retorno;
	}
	
	public static String removerAcentos(String acentuada) {  
	    CharSequence cs = new StringBuilder(acentuada);  
	    return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");  
	}
	
	
/*
	public Class<?> getImplementacao(Class<?> itf){
		return mapa.get(itf);
	}
	
	public <E> E getInstancia(Class<E> itf) throws Exception{
		Class<?> impl = mapa.get(itf);
		return (E) impl.newInstance();
	}
	
	public <E> E getInstancia(Class<E> itf, Object... params ) throws Exception{
		Class<?> impl = mapa.get(itf);
		Class<?>[] tiposParametros = new Class<?>[params.length];
		for(int i=0; i < tiposParametros.length; i++){
			tiposParametros[i] = params.getClass();
		}
		Constructor<?> c = impl.getConstructor(tiposParametros);
		return (E) impl.newInstance();
	}
*/

}
