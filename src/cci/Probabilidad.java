package cci;

import java.util.ArrayList;

//CODIGO PARA OBTENER ALFABETO FUENTE Y PROBABILIDAD ASOCIADA A CADA SIMBOLO

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import huffman.Huffman;

public class Probabilidad {

	public static void main(String [] args) {
		int base = 2;
		Scanner lector = new Scanner (System.in);
		String cadena = "";
		System.out.println ("Ingrese un texto: ");
		cadena = lector.nextLine();
		int size = cadena.length();
		System.out.println("Longitud de la cadena: " + size);
		Map<Character, Integer> numChars = getAlfabetoFuente(cadena);
		System.out.println("Tamaño del alfabeto fuente: " + numChars.size());
		iterarMap(numChars.entrySet().iterator());
		System.out.println("Base de codificacion: "+base);
		
		//CALCULO DE LA PROBABILIDAD ASOCIADA A CADA SIMBOLO
		System.out.println("\nProbabilidad asociada a cada simbolo.");
	    System.out.println("P(Si) = Count(Si)/Longitud del mensaje recibido");
		Map<Character, Double> pps = getProbabilidadPorSimbolo(numChars, size);
		Iterator<Entry<Character, Double>> i = pps.entrySet().iterator();
		Iterator<Entry<Character, Integer>> j = numChars.entrySet().iterator();
		while (i.hasNext()) {
		    Map.Entry<Character, Double> pairi = (Map.Entry<Character, Double>)i.next();
		    Map.Entry<Character, Integer> pairj = (Map.Entry<Character, Integer>)j.next();
		    System.out.println(pairi.getKey() + " -> " 
		    + pairj.getValue() + "/" + numChars.size() + " = " + round2(pairi.getValue()));
		}
		
		//CALCULO DE LA INFORMACION PROPIA DE CADA SIMBOLO
		System.out.println("\nInformacion propia asociada a cada simbolo.");
		System.out.println("I(Si) = logBASE(1/P(Si))");
		Map<Character, Double> ips = getInfoPropiaPorSimbolo(pps,base);
		Iterator<Entry<Character, Double>> ii = ips.entrySet().iterator();
		Iterator<Entry<Character, Double>> jj = pps.entrySet().iterator();
		while (ii.hasNext()) {
		    Map.Entry<Character, Double> pairi = (Map.Entry<Character, Double>)ii.next();
		    Map.Entry<Character, Double> pairj = (Map.Entry<Character, Double>)jj.next();
		    System.out.println(pairi.getKey() + " -> "
		    		+ "log"+ base + "(1/" + round2(pairj.getValue()) + ") = " + round2(pairi.getValue()) + " bits");
		}
		
		//CALCULO DE LA ENTROPIA
		System.out.println("\nEntropia de la informacion:");
		double H = getEntropia(pps, ips);
		System.out.println(round2(H) + " bits");
		
		//CALCULO DEL TAMANIO MINIMO DEL CODIGO DE BLOQUE ASOCIADO
		System.out.println("\nTamaño minimo del codigo de bloque asociado: ");
		double k = getCodigoDeBloqueMA(numChars.size(),base);
		System.out.println("log"+ base + "(" + numChars.size() + ") = " + k);
		
		//HUFFMAN CODIFICACION
		System.out.println("\n----------HUFFMAN----------");
		Huffman huffmanCode =  new Huffman(cadena);
		char[] af = huffmanCode.getAf();
		int[] it = huffmanCode.getIt();
		ArrayList<String> h = huffmanCode.getH();
		System.out.println("\nSe aplicará huffman al siguiente mensaje:" + cadena);
		System.out.println("\nAlfabeto fuente ordenado:");
		for (int x = 0; x < af.length; x++) {
			System.out.println(af[x] + " = " + it[x]);
		}
		System.out.println("\nEl resultado fue: ");
		for (int x = 0; x < h.size(); x++) {
			System.out.println(af[x] + " = " + h.get(x));	
		}
		
		//LONGITUD PROMEDIO DEL CODIGO HUFFMAN
		double lph = huffmanCode.getLongitudPromedio(pps);
		System.out.println("\nLongitud promedio del codigo de huffman: " + round2(lph));
		
		//EFICIENCIA DEL CODIGO DE HUFFMAN
		double efhuffman = H/lph;
		System.out.println("\nEficiencia del codigo de huffman -> H(S)/L = " + round2(H)  + "/" + round2(lph) + " = " + round2(efhuffman));
		
		lector.close();
	}
	
	public static double round2(Double d) {
		return Math.round(d*100.0)/100.0;
	}
	
	public static void iterarMap(Iterator i) {
		while (i.hasNext()) {
		    Map.Entry<Character, Double> pair = (Map.Entry)i.next();
		    System.out.println(pair.getKey() + " = " + pair.getValue());
		}
	}
	
	public static Map<Character, Integer> getAlfabetoFuente(String cadena) {
		Map<Character, Integer> numChars = new HashMap<Character, Integer>();

		for (int i = 0; i < cadena.length(); ++i)
		{
		    char charAt = cadena.charAt(i);

		    //Se busca si la letra en el HashMap, como no existe la agregamos con un '1'
		    if (!numChars.containsKey(charAt))
		    {
		        numChars.put(charAt, 1);
		    }
		    else
		    {
		        //Como la letra ya existe vamos por el contador de esa letra y 
		        //le sumamos uno y la agregamos 'remplazando' la letra anterior
		        numChars.put(charAt, numChars.get(charAt) + 1);
		    }
		}
		
		return numChars;
	}
	
	private static Map<Character,Double> getProbabilidadPorSimbolo(Map<Character, Integer> numChars, int size){
		Map<Character, Double> pps = new HashMap<Character, Double>();
		Iterator it = numChars.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry pair = (Map.Entry)it.next();
		    String pValue = "" + pair.getValue();
		    Double probabilidad = Double.parseDouble(pValue)/size;
		    String pKey = "" + pair.getKey();
		    Character c = pKey.charAt(0);
		    pps.put(c, probabilidad);
		}
		return pps;
	}
	
	private static Map<Character,Double> getInfoPropiaPorSimbolo(Map<Character, Double> pps, int base){
		Map<Character, Double> ips = new HashMap<Character, Double>();
		Iterator it = pps.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry pair = (Map.Entry)it.next();
		    String pValue = "" + pair.getValue();
		    Double infoPropia = Math.log(1/Double.parseDouble(pValue))/ Math.log(base);
		    String pKey = "" + pair.getKey();
		    Character c = pKey.charAt(0);
		    ips.put(c, infoPropia);
		}
		return ips;
	}
	
	private static double getEntropia(Map<Character, Double> pps,Map<Character, Double> ips) {
		Iterator it1 = pps.entrySet().iterator();
		Iterator it2 = ips.entrySet().iterator();
		
		double h = 0;
		while (it1.hasNext()) {
		    Map.Entry pair1 = (Map.Entry)it1.next();
		    Map.Entry pair2 = (Map.Entry)it2.next();
		    String pValue1 = "" + pair1.getValue();
		    String pValue2 = "" + pair2.getValue();
		    h += Double.parseDouble(pValue1) * Double.parseDouble(pValue2);
		}
		return h;
	}
	
	private static double getCodigoDeBloqueMA(int size, int base) {
		return Math.log(size)/ Math.log(base);
	}
}
