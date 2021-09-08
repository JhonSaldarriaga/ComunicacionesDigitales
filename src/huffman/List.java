package huffman;

public class List {

	private NodeList root;
	private int size;
	
	public List() {
		root = null;
		size = 0;
	}
	
	public void add(Node n) {
		if(root == null) {
			root = new NodeList(n);
		}else {
			NodeList aux = root;
			while(aux.getNext()!=null) {
				aux = aux.getNext();
			}
			NodeList newNode = new NodeList(n);
			aux.setNext(newNode);
			newNode.setPrev(aux);
		}
		size+=1;
	}
	
	public void add(Node n, int v) {
		NodeList aux = root;
		while(aux.getNext() != null && aux.getNext().getValue().getValue()<=v) {
			aux = aux.getNext();
		}
		NodeList newNode = new NodeList(n);
		if(aux.getNext()==null) {
			aux.setNext(newNode);
			newNode.setPrev(aux);
		}else {
			aux.getNext().setPrev(newNode);
			newNode.setNext(aux.getNext());
			newNode.setPrev(aux);
			aux.setNext(newNode); 
		}
		size+=1;
	}
	
	public Node getValueByIndex(int i) {
		int cont = 0;
		NodeList aux = root;
		while(cont<i) {
			aux = aux.getNext();
			cont++;
		}
		return aux.getValue();
	}

	public String showList() {
		NodeList aux = root;
		String r = "";
		while(aux != null) {
			r += "" + aux.getValue().getValue();
			aux = aux.getNext();
		}
		return r;
	}
	
	public NodeList getRoot() {
		return root;
	}
	
	public int getSize() {
		return size;
	}
}
