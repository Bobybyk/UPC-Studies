package controler;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import model.BufferedModel;

public final class ControllerCircle extends Controller {

	private int posX,posY, lastPosX, lastPosY;

	public ControllerCircle(BufferedModel model, JPanel ardoise, String name) {
		super(model, ardoise, name);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.posX = e.getX();
		this.posY = e.getY();
		this.lastPosX = e.getX();
		this.lastPosY = e.getY();
		model.getGraphics().setXORMode(Color.black);
		model.getGraphics().drawOval(posX, posY, lastPosX - posX, lastPosY - posY);
		model.notifyObserver();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		model.getGraphics().drawOval(posX, posY, lastPosX - posX, lastPosY - posY);
		lastPosX = e.getX();
		lastPosY = e.getY();
		model.getGraphics().drawOval(posX, posY, lastPosX - posX, lastPosY - posY);
		model.notifyObserver();
	}
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		model.getGraphics().drawOval(posX, posY, lastPosX - posX, lastPosY - posY);
		model.getGraphics().setPaintMode();
		model.getGraphics().drawOval(posX, posY, e.getX() - posX, e.getY() - posY);
		model.notifyObserver();
	}

}
