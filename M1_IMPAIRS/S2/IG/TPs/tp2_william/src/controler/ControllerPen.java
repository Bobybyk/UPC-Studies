package controler;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import model.BufferedModel;

public final class ControllerPen extends Controller {

	private int posX,posY;
	
	public ControllerPen(BufferedModel model, JPanel ardoise, String name) {
		super(model, ardoise, name);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		this.posX = e.getX();
		this.posY = e.getY();
		model.getGraphics().drawLine(posX, posY, posX, posY);
		model.notifyObserver();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		model.getGraphics().drawLine(posX, posY, e.getX(), e.getY());
		this.posX = e.getX();
		this.posY = e.getY();
		model.notifyObserver();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		model.getGraphics().drawLine(posX, posY, e.getX(), e.getY());
		model.notifyObserver();
	}

}
