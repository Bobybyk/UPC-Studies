package server.elements;

public abstract class Element {
	protected char label;

	protected int x,y;

	public Element(int x, int y, char c) {
		this.label = c;
		this.x = x;
		this.y = y;
	}

	public Element(char c) {
		this.label = c;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public String toString() {
			return label+""/*+" "+x+" "+y*/;
	}
}
