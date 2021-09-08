package huffman;

public class Node {

	private Node left;
	private Node right;
	private Node father;
	private int value;
	private boolean rson;
	private boolean lson;
	
	public Node(Node left, Node right, int value) {
		father = null;
		this.left = left;
		this.right = right;
		this.value = value;
		rson = false;
		lson = false;
	}
	
	public Node(int value) {
		father = null;
		this.left = null;
		this.right = null;
		this.value = value;
		rson = false;
		lson = false;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isRson() {
		return rson;
	}

	public void setRson(boolean rson) {
		this.rson = rson;
	}

	public boolean isLson() {
		return lson;
	}

	public void setLson(boolean lson) {
		this.lson = lson;
	}
}
