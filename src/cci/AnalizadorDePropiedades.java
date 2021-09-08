package cci;

import java.util.ArrayList;

//ANALIZADOR DE PROPIEDADES DE UNA CODIFICACION.
public class AnalizadorDePropiedades {

	public static void main(String args[]) {
		getDesigualdadKraft();
	}
	
	private static void getDesigualdadKraft() {
		//DESIGUALDAD DE CRAFT
		ArrayList<String> cod = new ArrayList<>();
		//Lista de las palabras del alfabeto fuente codificadas.
		cod.add("0");//1
		cod.add("10");//2
		cod.add("110");//3
		cod.add("11");//2
		int r = 2;
		//Tamaño del alfabeto código, es 2 cuando se codifica a binario 0,1
		double k = DesigualdadKraft.getKbyCod(cod, r);
		
		if(k>1)
			System.out.println("Desigualdad de Kraft: " + k + 
					"\nNo es instantaneo y no se puede rediseñar");
		else
			System.out.println("Desigualdad de Kraft: " + k + 
					"\nEs instantaneo. Si no es así, se puede rediseñar");
	}
}
