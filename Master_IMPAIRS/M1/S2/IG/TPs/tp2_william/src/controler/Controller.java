package controler;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.Icon;
import javax.swing.JPanel;

import model.BufferedModel;

public abstract class Controller extends MouseAdapter {
	
	protected BufferedModel model;
	protected JPanel ardoise;
	protected String name;
	
	public Controller(BufferedModel model, JPanel ardoise, String name){
		this.model = model;
		this.ardoise = ardoise;
		this.name = name;
	}
	
	final public void activer() {
		ardoise.addMouseListener(this);
		ardoise.addMouseMotionListener(this);
	}
	
	final public void desactiver() {
		ardoise.removeMouseListener(this);
		ardoise.removeMouseMotionListener(this);	
	}

	public void clear() {
		model.getGraphics().clearRect(0, 0, 800, 800);
		model.notifyObserver();
	}

	public BufferedModel getModel() {
		return model;
	}

	public JPanel getArdoise() {
		return ardoise;
	}

	public String getName() {
		return name;
	}
	
	public void updateColor(Color color) {
		model.setColorPaint(color);
	}
}
