package cci;//CONSTRUCCION DE CODIGOS INSTANTANEOS

import java.util.ArrayList;

public class DesigualdadKraft {

	/**La desigualdad de Kraft da la longitud mínima que puede usarse para diseñar
	un código intantáneo. Si el resultado es menor a 1, entonces el código es
	instantáneo o puede ser rediseñado para serlo.*/

	public static double getKbyCod(ArrayList<String> cod, int r) {
		double k = 0;
		for (int i = 0; i < cod.size(); i++) {
			k += 1.0/(Math.pow(r, cod.get(i).length()));
		}
		
		return k;
	}
	
	public static double getKbyLength(ArrayList<Integer> cod, int r) {
		double k = 0;
		for (int i = 0; i < cod.size(); i++) {
			k += 1.0/(Math.pow(r, cod.get(i)));
		}
		return k;
	}
}
