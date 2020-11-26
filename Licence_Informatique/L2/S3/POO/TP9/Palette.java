public class Palette {
	private Vue view;
	private Modele mod;
	private Controleur control;

	public Palette(Modele mod) {
		this.mod = mod;
		this.view = new Vue(mod);
		this.view.setVisible(true);
		this.control = new Controleur(this.mod, this.view);
	}

	public static void main (String[] args) {
		Modele mod = new Modele(0, 100, 0);
		new Palette(mod);
	}
}