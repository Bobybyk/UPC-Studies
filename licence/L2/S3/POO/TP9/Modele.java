import javax.swing.*;
import java.awt.*;

public class Modele {
	private Color color;
	private Color memoire;

	public Modele (int r, int g, int b) {
		this.color = new Color(r, g, b);
	}	

	public Color getColor() {
		return this.color;	
	}

	public void setColor(Color color) {
		this.color = color;	
	}
	public void setMemoire() {
		this.memoire = color;
	}

	public Color getMemoire() {
		return this.memoire;
	}
}