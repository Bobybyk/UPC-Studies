import javax.swing.JFrame;

import view.JPanelPaint;
import view.MonJMenuBar;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MonJMenuBar jmenuBar = new MonJMenuBar();
		frame.setJMenuBar(jmenuBar);
		
		JPanelPaint jpanelPaint = new JPanelPaint();
		frame.getContentPane().add(jpanelPaint);
		frame.setVisible(true);
		frame.pack();		
	}
}
