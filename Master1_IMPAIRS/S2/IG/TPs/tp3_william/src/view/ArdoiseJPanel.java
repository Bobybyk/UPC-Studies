package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.w3c.dom.css.RGBColor;

import model.BufferedModel;
import utils.Observable;
import utils.Observer;

public class ArdoiseJPanel extends JPanel implements Observer{
	
	private BufferedModel model;
	
	public ArdoiseJPanel(int width, int height) {
		setBorder(BorderFactory.createLineBorder(Color.gray, 3));
		setPreferredSize(new Dimension(width, height));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(model, 0, 0, null);
	}

	@Override
	public void update(BufferedModel model) {
		this.model = model;
		repaint();
	}


}
