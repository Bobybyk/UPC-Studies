import javax.swing.*;
import java.awt.*;

public class Vue extends JFrame {
	private JPanel panneauColore;
	private JLabel etiqCouleur;
	private JPanel panneauChoix;
	private Modele mod;
	private Controleur control;
	private JButton mem = new JButton("mémoriser");
	private JButton rem = new JButton("s'en rappeler");
	private JButton com = new JButton("complementaire");
	private JSlider rougeSlide = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	private JSlider vertSlide = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
	private JSlider bleuSlide = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);

	public Vue(Modele mod) {
		
		this.setTitle("Palette"); // Création du titre
		this.setSize(800, 600); // Définition de la résolution
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.mod = mod;
		this.panneauColore = new JPanel(); //création du panneau de couleur
		this.panneauChoix = new JPanel(); // création du panneau de controle 
		
		Color vert = new Color(0, 100, 0); //création de la couleure verte
		
		this.panneauColore.setBackground(vert); //implémentation de la couleure verte
		this.getContentPane().add(panneauColore); //ajout de la couleur verte au panel
		this.etiqCouleur = new JLabel(); //création du label
		this.etiqCouleur.setText("Vert"); //Ajout du label
		this.panneauColore.add(etiqCouleur, BorderLayout.CENTER);
		this.panneauColore.setLayout(new GridLayout());	//centrage vertical
		this.etiqCouleur.setHorizontalAlignment(0);	//centrage horizontal
		this.panneauColore.add(panneauChoix);
		
		//Ajout des boutons
		this.panneauChoix.add(this.mem);
		this.panneauChoix.add(this.rem);
		this.panneauChoix.add(this.com);
		//Ajout des Sliders
		this.panneauChoix.add(this.rougeSlide);
		this.panneauChoix.add(this.vertSlide);
		this.panneauChoix.add(this.bleuSlide);
	}

	void miseAJour() {
		this.panneauColore.setBackground(mod.getColor());
		//this.etiqCouleur.setText(mod.getHexa()); //L'appel qu'il faudrait faire à une méthode getHexa() pour récupérer la valeur hexadécimal (pas réussi) 
	}

	JSlider getRouge() {
		return this.rougeSlide;
	}
	JSlider getVert() {
		return this.vertSlide;
	}
	JSlider getBleu() {
		return this.bleuSlide;
	}

	JButton getMem() {
		return this.mem;
	}
	JButton getRem() {
		return this.rem;
	}
	JButton getCom() {
		return this.com;
	}
}