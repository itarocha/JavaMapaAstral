package br.itarocha.carta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
/*
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
*/

class Pojo
{
String name;
String age;
//generate setter and getters
}

public class Main {

	private List<Cidade> listaCidades = new ArrayList<Cidade>();
	
	public Main(){
		carregarArquivoCidades();
	}

	// ("Itamar","1972.6.29","5.0.0", Caxias, MA
	public void go(String nome, String dataAAAAMMDD, String horaHHMMSS, String cidade, String uf ){
		ConstrutorMapa construtor = new ConstrutorMapa();
		DecoradorMapa decorador = new DecoradorMapa();

		//Cidade c = getCidade("CAxíAS","ma");
		//Cidade c = getCidade("Uberlândia","mG");
		Cidade c = getCidade(cidade, uf);
		if (c.getCodigo() > 0){
    		Mapa mapa 	= construtor.buildMapa(nome, dataAAAAMMDD, horaHHMMSS,-3, c.getLatitude(),c.getLongitude());
    		String json = decorador.getJSON(mapa);
    		System.out.println(json);
		}
		
		
		//Mapa mapaItamar 	= construtor.buildMapa("Itamar","1972.6.29","5.0.0",-3,"-4.51.32","-43.21.22");
		//Mapa mapaSaoPaulo	= construtor.buildMapa("São Paulo","2014.10.8","17.0.0",-3,"-23.32.51","-46.38.10");
		//Mapa mapaLivia		= construtor.buildMapa("Lívia","2001.9.28","7.0.0",-3,"-18.55.7","-48.16.38");
		//Mapa mapaSibele		= construtor.buildMapa("Sibele", "1978.9.30","10.0.0",-3,"-18.55.7","-48.16.38");
		//Mapa mapaRosangela	= construtor.buildMapa("Rosangela", "1983.1.15","07.30.0",-3,"-18.52.14","-48.52.51");
		//mapaLivia.setNome("Lívia");


		//System.out.println("\n**********************************************\n");

		/*
		decorador.display(mapaLivia);
		System.out.println("\n**********************************************\n");
		//construtor.display(mapaSaoPaulo);
		//System.out.println("\n**********************************************\n");
		decorador.display(mapaSibele);
		System.out.println("\n**********************************************\n");
		decorador.display(mapaRosangela);
		*/
	}
	
	//http://th-mack.de/international/download/index.html
	//http://www.radixpro.org/fix-east-west.html
	public static void main( String[] args) {
		
		if (args.length != 5){
			System.out.println("Entre com os 5 parâmetros com espaço");
			System.out.println("Nome");
			System.out.println("Data de nascimento formato AAAA.MM.DD");
			System.out.println("Hora de nascimento formato HH.MM.SS");
			System.out.println("Cidade de nascimento");
			System.out.println("UF de nascimento");
			System.exit(0);
		}
		
		String nome = args[0];
		String data = args[1];
		String hora = args[2];
		String cidade = args[3];
		String uf = args[4];
		
		//String entrada = "Gisele Bündchen da Conceição e Silva foi batizada assim em homenagem à sua conterrânea de Uberlândia";
		//System.out.println(removerAcentos(entrada).toLowerCase());
		
		
		
		
		Main m = new Main();
		m.go(nome, data, hora, cidade, uf);
		//m.go("Itamar Rocha", "1972.06.29", "05.00.00", "Caxias", "MA");
		
		
		//Conversions.GrauDecimalParaGrauSexagesimal(-43.3532d);
		//Conversions.GrauDecimalParaGrauSexagesimal(-4.8655d);
		
		
	}
	
	private void carregarArquivoCidades(){
		
	    String arquivoCSV = ".//ephe//cidades_brasil.csv ";
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
		Cidade retorno = new Cidade();
		String nomeSemAcento = removerAcentos(nome).toLowerCase();
		uf = uf.toLowerCase();
		for (Cidade c : listaCidades){
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
	public void http(String url, String body) throws ClientProtocolException, IOException  {

		 
		 HttpClient httpclient = HttpClient.createDefault();
		 HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");

		 // Request parameters and other properties.
		 List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		 params.add(new BasicNameValuePair("param-1", "12345"));
		 params.add(new BasicNameValuePair("param-2", "Hello!"));
		 httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		 //Execute and get the response.
		 HttpResponse response = httpclient.execute(httppost);
		 HttpEntity entity = response.getEntity();

		 if (entity != null) {
		     InputStream instream = entity.getContent();
		     try {
		         // do something useful
		     } finally {
		         instream.close();
		     }
		 }		 
		 
		 
		 
// http://www.devmedia.com.br/trabalhando-com-json-em-java-o-pacote-org-json/25480		 
		 
		 String postUrl="www.site.com";// put in your url
		 
		 HttpClient httpClient = new DefaultHttpClient();  
		 Gson gson= new Gson();
		 HttpPost post = new HttpPost(postUrl);
		 Pojo pojo1 = new Pojo();
		 StringEntity postingString = null;
		 postingString = new StringEntity(gson.toJson(pojo1));
		 post.setEntity(postingString);
		 post.setHeader("Content-type", "application/json");

		 HttpResponse  response = httpClient.execute(post);
		 
		 
	        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
	            HttpPost request = new HttpPost(url);
	            StringEntity params = new StringEntity(body);
	            request.addHeader("content-type", "application/json");
	            request.setEntity(params);
	            HttpResponse result = httpClient.execute(request);
	            String json = EntityUtils.toString(result.getEntity(), "UTF-8");

	            com.google.gson.Gson gson = new com.google.gson.Gson();
	            Response respuesta = gson.fromJson(json, Response.class);

	            System.out.println(respuesta.getExample());
	            System.out.println(respuesta.getFr());

	        } catch (IOException ex) {
	        }
	        return null;
	        
	    }
	 */
	
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