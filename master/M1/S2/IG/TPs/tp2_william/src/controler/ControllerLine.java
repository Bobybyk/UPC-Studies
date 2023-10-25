package controler;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import model.BufferedModel;

public final class ControllerLine extends Controller {
	
	private int posX,posY , lastPosX, lastPosY;


	public ControllerLine(BufferedModel model, JPanel ardoise, String name) {
		super(model, ardoise, name);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.posX = e.getX();
		this.posY = e.getY();
		this.lastPosX = e.getX();
		this.lastPosY = e.getY();
		model.getGraphics().setXORMode(Color.black);
		model.getGraphics().drawLine(posX, posY, posX, posY);
		model.notifyObserver();
	}
		
	@Override
	public void mouseDragged(MouseEvent e) {
		model.getGraphics().drawLine(posX, posY, lastPosX, lastPosY);
		lastPosX = e.getX();
		lastPosY = e.getY();
		model.getGraphics().drawLine(posX, posY, lastPosX, lastPosY);
		model.notifyObserver();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		model.getGraphics().drawLine(posX, posY, lastPosX, lastPosY);
		model.getGraphics().setPaintMode();
		model.getGraphics().drawLine(posX, posY, e.getX(), e.getY());
		model.notifyObserver();
	}

}