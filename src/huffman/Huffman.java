package huffman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import cci.Probabilidad;

public class Huffman {

	private char[] af;
	private int[] it;
	private ArrayList<String> h;
	private String msg;
	
	public Huffman(String msg) {
		this.msg = msg;
		runHuffman();
	}
	
	private void runHuffman() {
		//ALFABETO FUENTE RESPECTIVO
		//char[] af = {'C','F','M','P','G','H','N','B','D','R','T','S','*','O'};
		//CANTIDAD DE VECES QUE SE REPITE CADA SIMBOLO DEL AF RESPECTIVO, ORDENADO
		//int[] it = {1,1,1,1,2,2,2,3,3,3,3,4,7,11};
		//String msg = "CADA DIA SABEMOS MAS Y ENTENDEMOS MENOS";
		
		Map<Character, Integer> numChars = Probabilidad.getAlfabetoFuente(msg);
		af = new char[numChars.size()];
		it = new int[numChars.size()];
		
		Iterator<Entry<Character, Integer>> itera = numChars.entrySet().iterator();
		int index = 0;
		while (itera.hasNext()) {
		    Map.Entry<Character, Integer> pair = (Map.Entry<Character, Integer>)itera.next();
		    af[index] = pair.getKey();
		    it[index] = pair.getValue();
		    index++;
		}
		
		for (int i = 0; i < it.length; i++) {
	        for (int j = 1; j < (it.length - i); j++) {
	            if (it[j - 1] > it[j]) {
	                int temp = it[j - 1];
	                char temp2 = af[j-1];
	                it[j - 1] = it[j];
	                af[j - 1] = af[j];
	                it[j] = temp;
	                af[j] = temp2;
	            }
	        }
		}
		h = solveHuffman(it);
	}
	
	private void getHuffman(List l) {
		int index0 = 0;
		int index1 = 1;
		while(index1<l.getSize()) {
			Node v0 = l.getValueByIndex(index0);
			Node v1 = l.getValueByIndex(index1);
			int v = v0.getValue() + v1.getValue();
			Node f = new Node(v1,v0,v);
			v0.setFather(f);
			v0.setRson(true);
			v1.setFather(f);
			v1.setLson(true);
			l.add(f, v);
			
			index0+=2;
			index1+=2;
		}
	}
	
	private ArrayList<String> solveHuffman(int[] it) {
		List l = arrayToList(it);
		getHuffman(l);
		ArrayList<String> r = new ArrayList<>() ;
		
		for(int i = 0; i<l.getSize(); i++) {
			String reply = "";
			Node aux = l.getValueByIndex(i);
			if(aux.getLeft()==null && aux.getRight()==null) {
				while(aux.getFather()!=null) {
					if(aux.isLson()) {
						reply += "0";
					}else if(aux.isRson()){
						reply += "1";
					}
					aux = aux.getFather();
				}
				if(aux.isLson()) {
					reply += "0";
				}else if(aux.isRson()){
					reply += "1";
				}
				r.add(invString(reply));
			}
		}
		
		return r;
	}
	
	private String invString(String s) {
		String inv = "";
		
		for (int x=s.length()-1;x>=0;x--)
			  inv += s.charAt(x);
		
		return inv;
	}
	
	private List arrayToList(int[] r) {
		List l = new List();
		for (int i = 0; i < r.length; i++) {
			l.add(new Node(r[i]));
		}
		return l;
	}
	
	public double getLongitudPromedio(Map<Character, Double> pps) {
		
		double lp = 0;
		for(int i = 0; i<pps.size() ; i++) {
			lp += pps.get(af[i])*h.get(i).length();
		}
		
		return lp;
	}

	public char[] getAf() {
		return af;
	}

	public int[] getIt() {
		return it;
	}

	public ArrayList<String> getH() {
		return h;
	}
}
