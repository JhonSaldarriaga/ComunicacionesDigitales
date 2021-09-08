package lzw;

import java.util.ArrayList;

public class Zip {

	public static void main (String [] args) {
		ArrayList<String> fuente = new ArrayList<>();
		addStringToArrayList(fuente, "DOCTOR BOB GORDON SHOPS HOT DOGS FROM BOSTON");
		ArrayList<String> dicc = getAlfabetoFuente("DOCTOR BOB GORDON SHOPS HOT DOGS FROM BOSTON");
		
		String w = "";
		String k;
		String reply = "";
		
		boolean eof = false;
		int indice = 0;
		while(!eof) {
			k = fuente.get(indice);
			indice += 1;
			//System.out.println("w: " + w + ", k: " + k + ", wk: " + w + k);
			if(dicc.contains(w+k)) {
				w=w+k;
			}else {
				reply += dicc.indexOf(w) + " ";
				dicc.add(w+k);
				w=k;
			}
			if(indice == fuente.size()) {
				eof = true;
			}
		}
		reply += dicc.indexOf(w) + " ";
		System.out.println(reply);
		for (int i = 0; i < dicc.size(); i++) {
			System.out.println(dicc.get(i) + ":" + i);
		}
	}
	
	public static ArrayList<String> getAlfabetoFuente(String cadena){
		ArrayList<String> numChars = new ArrayList<String>();

		for (int i = 0; i < cadena.length(); ++i){
		    char charAt = cadena.charAt(i);
		    
		    if (!numChars.contains(""+charAt))
		    {
		        numChars.add(""+charAt);
		    }
		}
		
		return numChars;
	}
	
	public static void addStringToArrayList(ArrayList<String> list, String cadena) {
		for (int i = 0; i < cadena.length(); ++i){
		    list.add(""+cadena.charAt(i));
		}
	}
}
