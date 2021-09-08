package lzw;

import java.util.ArrayList;

public class Unzip {

	public static void main (String [] args) {
		ArrayList<Integer> cod = new ArrayList<>();
		addStringToArrayList(cod, "0 1 2 3 1 4 5 6 1 6 5 7 18 14 8 5 9 10 1 11 9 5 31 3 5 14 7 34 12 4 1 13 20 1 9 17 8 ");
		ArrayList<String> dicc = Zip.getAlfabetoFuente("DOCTOR BOB GORDON SHOPS HOT DOGS FROM BOSTON");
		System.out.println(cod);
		System.out.println(dicc);
		
		int codViejo;
		int codNuevo;
		String cadena;
		String caracter;
		codViejo = cod.get(0);
		caracter = dicc.get(codViejo);
		String reply = "";
		reply += caracter;
		
		boolean eof = false;
		int indice = 1;
		while(!eof) {
			codNuevo = cod.get(indice);
			indice += 1;
			if(codNuevo>= dicc.size()) {
				cadena = dicc.get(codViejo);
				cadena = cadena + caracter;
			}else {
				cadena = dicc.get(codNuevo);
			}
			reply += cadena;
			caracter = ""+cadena.charAt(0);
			dicc.add(dicc.get(codViejo)+caracter);
			codViejo = codNuevo;
			if(indice == cod.size()) {
				eof = true;
			}
		}
		System.out.println(reply);
	}
	
	public static void addStringToArrayList(ArrayList<Integer> list, String cadena) {
		String n = "" + cadena.charAt(0);
		
		for (int i = 1; i < cadena.length(); ++i){
		    if((""+cadena.charAt(i)).equals(" ")) {
		    	list.add(Integer.parseInt(n));
		    	n = "";
		    }
		    else
		    	n += cadena.charAt(i);
		}
	}
}
