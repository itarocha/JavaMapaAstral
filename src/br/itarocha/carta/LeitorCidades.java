package br.itarocha.carta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorCidades {
	
	public static void leArquivoCsv(){

		try {
			BufferedReader StrR = new BufferedReader(new FileReader("c:\\tabela.csv"));
	
			String Str;
			String[] TableLine;
	
			while((Str = StrR.readLine())!= null){
				TableLine = Str.split(";");
				for (String cell : TableLine) { 
					System.out.print(cell+" "); 
				}
				System.out.println("\n");
			}
			StrR.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}

}
