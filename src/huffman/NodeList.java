package huffman;

public class NodeList {

	private NodeList next;
	private NodeList prev;
	private Node value;
	
	public NodeList(NodeList next, NodeList prev, Node value) {
		this.next = next;
		this.prev = prev;
		this.value = value;
	}
	
	public NodeList(Node value) {
		this.next = null;
		this.prev = null;
		this.value = value;
	}

	public NodeList getNext() {
		return next;
	}

	public void setNext(NodeList next) {
		this.next = next;
	}

	public NodeList getPrev() {
		return prev;
	}

	public void setPrev(NodeList prev) {
		this.prev = prev;
	}

	public Node getValue() {
		return value;
	}

	public void setValue(Node value) {
		this.value = value;
	}
}
