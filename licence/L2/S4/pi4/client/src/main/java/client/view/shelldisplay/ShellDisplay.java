package client.view.shelldisplay;

// import our Classes
import server.playingProps.Board;
import server.elements.*;


public class ShellDisplay {
    Board b;
    
    //Constructor
    public ShellDisplay(Board b) {
    	this.b = b;
    }
    
    //Display the content of a Board in the shell
    public void displayBoard() {
    	this.displayInformations();
    	for(int y = 0; y<b.getElement().length; y++) {
    		for(int x = 0; x<b.getElement()[y].length; x++) {
    			if(b.getElementAt(x, y) == null) {
    				System.out.print(".");
    			}
    			else {
    				System.out.println(b.getElementAt(x, y).toString());
    			}
    		}
    		System.out.println("");
    	}
    	System.out.println("");
    }
    
    //Display informations about present elements on the board
    public void displayInformations() {
    	int voidCounter = 0;
    	int wallCounter = 0;
    	int treasureCounter = 0;
    	int holeCounter = 0;
    	for(int y = 0; y<b.getElement().length; y++) {
    		for(int x = 0; x<b.getElement()[y].length; x++) {
    			if(b.getElementAt(x, y) == null) {
    				voidCounter++;
    			}
    			if(b.getElementAt(x, y) instanceof Wall) {
    				wallCounter++;
    			}
    			if(b.getElementAt(x, y) instanceof Treasure) {
    				treasureCounter++;
    			}
    			if(b.getElementAt(x, y) instanceof Hole) {
    				holeCounter++;
    			}
    		}
    	}
    	System.out.println("Voids : "+voidCounter+" / Walls : "+wallCounter+" / Treasures : "+treasureCounter+" / Holes : "+holeCounter);
    }
    
}
