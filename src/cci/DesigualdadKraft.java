package cci;//CONSTRUCCION DE CODIGOS INSTANTANEOS

import java.util.ArrayList;

public class DesigualdadKraft {

	/**La desigualdad de Kraft da la longitud m�nima que puede usarse para dise�ar
	un c�digo intant�neo. Si el resultado es menor a 1, entonces el c�digo es
	instant�neo o puede ser redise�ado para serlo.*/

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
