public abstract class Figure {
	private int posX;
	private int posY;
	public abstract void affiche();
	public abstract double estDistanDe(Figure fig);
	public abstract double surface();

	public Figure (int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public void deplacement (int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}

}