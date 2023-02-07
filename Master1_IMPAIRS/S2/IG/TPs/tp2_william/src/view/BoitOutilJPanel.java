package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.Controller;
import controler.ControllerCircle;
import controler.ControllerLine;
import controler.ControllerPen;

public class BoitOutilJPanel extends JPanel{
	
	private Controller courant;
	
	public BoitOutilJPanel(Controller courant){
		this.courant = courant;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		event("Couleur", (event -> courant.updateColor(JColorChooser.showDialog(this, TOOL_TIP_TEXT_KEY, getBackground()))));
		event("Effacer", (event -> this.courant.clear()));
		event(new ControllerPen(courant.getModel(), courant.getArdoise(), "Stylo"), true);
		event(new ControllerLine(courant.getModel(), courant.getArdoise(), "Ligne"), true);
		event(new ControllerCircle(courant.getModel(), courant.getArdoise(), "Cercle"), true);
	}
	
	
	public void event(String str, ActionListener action) {
		JButton button = new JButton(str);
		button.addActionListener(action);
		add(button);
	}
	
	public void event(Controller controler, boolean bool) {
		JButton button = new JButton(controler.getName());
		button.addActionListener(event ->{
			if(bool) {
				this.courant.desactiver();
				controler.activer();
				this.courant = controler;	
			}
		});
		add(button);
	}
	
}
