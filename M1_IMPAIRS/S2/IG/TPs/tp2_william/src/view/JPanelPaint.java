package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import controler.Controller;
import controler.ControllerCircle;
import controler.ControllerLine;
import controler.ControllerPen;
import model.BufferedModel;

public class JPanelPaint extends JPanel{
	
	public JPanelPaint() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		ArdoiseJPanel ardoise = new ArdoiseJPanel(800, 800);		
		BufferedModel model = new BufferedModel(800, 800, BufferedImage.TYPE_INT_RGB);
		model.subscribe(ardoise);
		model.notifyObserver();
		
		Controller c = new ControllerPen(model, ardoise, "");
		c.activer();
		add(ardoise);
		add(new BoitOutilJPanel(c));
	}

}
