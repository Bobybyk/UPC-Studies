public class Cellule2 {
	private int content;
	private Cellule2 next;
	private Cellule2 prev;
	

	public Cellule2 (int content, Cellule2 next, Cellule2 prev) {
		this.content = content;
		this.next = next;
		this.prev = prev;
	}

	public Cellule2 getNext() {
		return this.next;
	}
	public Cellule2 getPrev() {
		return this.prev;	
	}

	public void setNext(Cellule2 x) {
		this.next = x;
	}
	public void setPrev(Cellule2 x) {
		this.prev = x;
	}

}