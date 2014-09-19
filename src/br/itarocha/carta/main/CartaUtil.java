package br.itarocha.carta.main;

public class CartaUtil {

	public static String grauNaCasa(double d){
		return grau(d % 30);
	}
	
	public static String grau(double d) {
		d = Math.abs(d);
		d += 0.5/3600./10000.;	// round to 1/1000 of a second
		int deg = (int) d;
		d = (d - deg) * 60;
		int min = (int) d;
		d = (d - min) * 60;
		double sec = d;

		return String.format("%3d° %02d' %02.0f\"", deg, min, sec);
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
			retorno = "Cj";
		} else
		if ((resultado >= 52f) && (resultado < 69f)) {
			retorno = "Sx";
		} else
		if ((resultado >= 112f) && (resultado < 129f)) {
			retorno = "Tg";
		} else
		if ((resultado >= 82f) && (resultado < 99f)) {
			retorno = "Qd";
		} else
		if ((resultado >= 172f) && (resultado < 189f)) {
			retorno = "Op";
		}
		return retorno;
	}	
    
}
