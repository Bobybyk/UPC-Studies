import javax.swing.*;
import java.awt.*;

public class Controleur {
	private Modele mod;
	private Vue view;

	public Controleur(Modele mod, Vue view) {
		
		this.mod = mod;
		this.view = view;
		
		//Définition des confitions pour appeler les fonctions lambda
		view.getRouge().addChangeListener((event) -> this.slideMoved());
		view.getVert().addChangeListener((event) -> this.slideMoved());
		view.getBleu().addChangeListener((event) -> this.slideMoved());

		view.getMem().addActionListener((event) -> this.mod.setMemoire());
		view.getRem().addActionListener((event) -> this.remember());
		view.getCom().addActionListener((event) -> this.complementaire());
	}

	public void slideMoved() {
		int r = this.view.getRouge().getValue();
		int g = this.view.getVert().getValue();
		int b = this.view.getBleu().getValue();
		mod.setColor(new Color(r, g, b));
		this.view.miseAJour();
	}

	public void remember() {
		if (this.mod.getMemoire() != null) {
			this.mod.setColor(this.mod.getMemoire());
			this.view.getRouge().setValue(this.mod.getMemoire().getRed());
			this.view.getVert().setValue(this.mod.getMemoire().getGreen());
			this.view.getBleu().setValue(this.mod.getMemoire().getBlue());
			this.view.miseAJour();			
		} else { System.out.println("Aucune couleur en mémoire"); }
	}

	public void complementaire() {
		//définition des nouveaux paramètres (r, g, b) avec le complémentaire
		int r = 255-this.view.getRouge().getValue();
		int g = 255-this.view.getVert().getValue();
		int b = 255-this.view.getBleu().getValue();

		//modification des curseurs avec les nouveaux paramètres
		this.view.getRouge().setValue(r);
		this.view.getVert().setValue(g);
		this.view.getBleu().setValue(b);

		//Mise à jour de la vue
		this.view.miseAJour();
	}


}