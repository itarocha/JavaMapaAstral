package br.itarocha.carta;

class Pojo
{
	String name;
	String age;
	//generate setter and getters
}

public class Main {

	//http://th-mack.de/international/download/index.html
	//http://www.radixpro.org/fix-east-west.html
	public static void main( String[] args) {
		// Rode com os seguintes argumentos
		// "Itamar Rocha" "1972.06.29" "05.00.00" "Caxias" "MA"
		
		if (args.length < 5){
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
		
		Main m = new Main();
		m.go(nome, data, hora, cidade, uf);
	}
	
	public Main(){}

	// ("Itamar","1972.6.29","5.0.0", Caxias, MA
	public void go(String nome, String dataAAAAMMDD, String horaHHMMSS, String cidade, String uf ){
		ConstrutorMapa construtor = new ConstrutorMapa();
		DecoradorMapa decorador = new DecoradorMapa();

		Cidade c = new MapeadorCidades().getCidade(cidade, uf);
		if (c.getCodigo() > 0){
    		Mapa mapa 	= construtor.buildMapa(nome, dataAAAAMMDD, horaHHMMSS,-3, c.getLatitude(), c.getLongitude());
    		String json = decorador.getJSON(mapa);
    		System.out.println(json);
		} else {
			System.out.println("Não conseguiu localizar cidade");
		}
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