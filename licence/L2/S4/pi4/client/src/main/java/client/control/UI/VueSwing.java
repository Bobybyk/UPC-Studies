package client.control.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import client.control.UI.SceneSwing;
import client.commands.Command;
import client.GameInfo;


@SuppressWarnings("serial")
public class VueSwing extends JFrame implements MouseInputListener{
	JPanel Background = new JPanel();
	public static SceneSwing scene;
	public static int ID = 0;


	public VueSwing() {
		ID += 1;
		this.setTitle("Chasse au Trésor - ("+ID+")"); 


		int lines = GameInfo.getMap()[1];
		int columns = GameInfo.getMap()[0];

		this.setSize(columns*30+20, lines*28+50);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		scene = new SceneSwing();
		scene.addMouseListener(this);
		this.setContentPane(scene);
		this.setVisible(true);
		refreshScreen();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int screenX = e.getXOnScreen();
	    int screenY = e.getYOnScreen();
		System.out.println("Mouse clicked on ("+screenX+","+screenY+")");
		
		int x = screenX/(30) - 2;
		int y = screenY/(28) - 4;
		System.out.println("Position in tab = ("+x+","+y+")");
		
		this.setVisible(false);
		this.setVisible(true);
		//Request to move with x and y attributes
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
	
	Timer timer;
	public void refreshScreen() {
	    timer = new Timer(0, new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	        repaint();
	      }
	    });
	    timer.setRepeats(true);
	    timer.setDelay(17);
	    timer.start();
	  }
}