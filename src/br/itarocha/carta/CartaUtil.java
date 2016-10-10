package br.itarocha.carta;

public class CartaUtil {

	public static String grauNaCasa(double d){
		//return grau(d % 30);

		int[] g = grauToArray(d % 30);
		//return String.format("%3d° %02d' %02.0f\"", g[0], g[1], g[2]);
		return String.format("%02d.%02d.%02d", g[0], g[1], g[2]);
	}
	
	public static String grau(double d) {
		int[] g = grauToArray(d);
		//return String.format("%3d° %02d' %02.0f\"", g[0], g[1], g[2]);
		return String.format("%03d.%02d.%02d", g[0], g[1], g[2]);
	}

	public static int[] grauToArray(double d){
		int[] retorno = new int[3];

		d = Math.abs(d);
		d += 0.5/3600./10000.;	// round to 1/1000 of a second
		int deg = (int) d;
		d = (d - deg) * 60;
		int min = (int) d;
		d = (d - min) * 60;
		int sec = (int)d;

		retorno[0] = deg;
		retorno[1] = min;
		retorno[2] = sec;
		
		return retorno;
	}
	
    public static String signGlyphFromIndex(int i) {
        String c;
        switch (i) {
            case 0: c = "AR"; break;
            case 1: c = "l"; break;
            case 2: c = "m"; break;
            case 3: c = "n"; break;
            case 4: c = "o"; break;
            case 5: c = "p"; break;
            case 6: c = "q"; break;
            case 7: c = "r"; break;
            case 8: c = "s"; break;
            case 9: c = "t"; break;
            case 10: c = "u"; break;
            case 11: c = "v"; break;
            default: c = "-"; break;
        }
        return c;
    }

	public static String buildAspect(double a, double b){
		String retorno = "";
		
		if (Math.abs(b-a) > 190) {
			if (b < a) b += 360;
		}
		
		double resultado = Math.abs(b-a);
		if (resultado <= 8f){
			retorno = "cj";
		} else
		if ((resultado >= 52f) && (resultado < 69f)) {
			retorno = "sx";
		} else
		if ((resultado >= 112f) && (resultado < 129f)) {
			retorno = "tg";
		} else
		if ((resultado >= 82f) && (resultado < 99f)) {
			retorno = "qd";
		} else
		if ((resultado >= 172f) && (resultado < 189f)) {
			retorno = "op";
		}
		return retorno;
	}	
    
}
