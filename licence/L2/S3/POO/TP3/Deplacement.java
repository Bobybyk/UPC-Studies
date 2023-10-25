public class Deplacement {
	private int x0, y0, x1, y1;

	public Deplacement(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	public char typeDeplacement() {
		if (x0 == x1 && y0 != y1) { return 'v'; }
		if (y0 == y1 && x0 != x1) { return 'h'; }
		if (Math.abs(x0-x1) == Math.abs(y0-y1)) { return 'd'; }
		if (x0 == x1+2 || x0 == x1-2 || x0==x1+1 || x0==x1-1 && y0==y1+2 || y0==y1+2 || y0==y1+1 || y0==y1-1) { return 'c'; }
		return 'x';
	}

	public int dist() {
		if (this.typeDeplacement() == 'v' || this.typeDeplacement() == 'h') {
			return Math.abs(x0-x1+y0-y1);
		}
		if (this.typeDeplacement() == 'd') {
			return Math.abs(y0-y1);
		}
		else { return -1; }
	}

	public int getX0() {
		return this.x0;
	}
	public int getY0() {
		return this.y0;
	}
	public int getX1() {
		return this.x1;
	}
	public int getY1() {
		return this.y1;
	}
}