package view;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import controler.Controller;

public class MonJMenuBar extends JMenuBar{
	
	private Controller courant; 
	
	public MonJMenuBar() {
		//this.courant = courant;
		JMenu fileMenu = new JMenu("Fichier");
		
		JMenuItem saveFile = new JMenuItem("Sauvegarder image");
		JMenuItem loadFile = new JMenuItem("Charger image");

		JMenuItem closeApp = new JMenuItem("Quitter");
		closeApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		loadFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(closeApp);
		fileMenu.add(saveFile);
		fileMenu.add(loadFile);
		add(fileMenu);
		closeApp.addActionListener(event ->{
			System.exit(0);
		});
		
		
		loadFile.addActionListener(event ->{

		    JFileChooser choose = new JFileChooser(
		            FileSystemView
		            .getFileSystemView()
		            .getHomeDirectory()
		        );
		        
		        choose.setDialogTitle("Selectionnez une image");
		        choose.setAcceptAllFileFilterUsed(false);
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images JPG et GIF", "jpg", "gif");
		        choose.addChoosableFileFilter(filter);
		        int res = choose.showOpenDialog(null);
		        if (res == JFileChooser.APPROVE_OPTION) {
		          System.out.println(choose.getSelectedFile().getPath());
		          courant.clear();
		        }
		});

		
		
		saveFile.addActionListener(event ->{
			try {
				Random r = new Random();
				int randomnum =  r.nextInt(500);
			    File outputfile = new File("saved_image_" + randomnum + ".jpg");
			    ImageIO.write(courant.getModel(), "jpg", outputfile);
			    System.out.println("Image sauvegardé : " + "'saved_image_" + randomnum + ".jpg'");
			} catch (IOException e) {
			    System.out.println("Erreur impossible de sauvegarder l'image");
			}
		});

	}

}