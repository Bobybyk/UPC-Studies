import java.awt.image.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.io.*;
import javax.imageio.*;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;

public class ImageEditModel {
    private BufferedImage image;
    UndoManager undoManager = new UndoManager();
    
    public ImageEditModel(String path) throws IOException {
        try {
            this.image = ImageIO.read(new File(path));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveCut(Rectangle z) {
        BufferedImage im = this.image.getSubimage((int)z.getX(), (int)z.getY(), (int)z.getWidth(), (int)z.getHeight());
        Coupe c = new Coupe(z, (int) z.getWidth(), (int) z.getHeight(), im);
        c.doit();
        CutEdit cut = new CutEdit(c);
        this.undoManager.addEdit(cut); 
    }

    public void fillzone(Rectangle z, int [][] pixels) {
        for(int i = 0 ; i < (int) z.getWidth() ; i++) {
            for (int j = 0 ; j < (int) z.getHeight() ; j++) {
                this.image.setRGB(i+Math.abs((int) z.getX()), j+Math.abs((int) z.getY()), pixels[i][j]);
            }
        }
    }

    public void clearzone (Rectangle z) {
        Color color = Color.white;
        int rgBis = color.getRGB();
        int[][] pixels = new int[(int) z.getWidth()][(int) z.getHeight()];
        for (int i = 0 ; i < z.getWidth() ; i++) {
            for (int j = 0 ; j < z.getHeight() ; j++) {
                pixels[i][j] = rgBis;
            }
        }
        fillzone(z, pixels);
    }

    public int[][] getColor(Rectangle z, Color color) {
        return new int[(int) z.getWidth()][(int) z.getHeight()];
    }

    public BufferedImage getImage() {
        return this.image;
    }

    class Coupe {
        Rectangle rec;
        int[][] pixels;

        public Coupe(Rectangle rec, int largeur, int hauteur, BufferedImage image) {
            this.rec = rec;
            this.pixels = new int[largeur][hauteur];
            for (int i = 0 ; i < largeur ; i++) {
                for (int j = 0 ; j < hauteur ; j++) {
                    this.pixels[i][j] = image.getRGB(i, j);
                }
            }
        }

        void doit() {
            clearzone(this.rec);
        }

        void undo() {
            fillzone(this.rec, this.pixels);
        }
    }

    class CutEdit extends AbstractUndoableEdit {
        private static final long serialVersionUID = 1L;
        Coupe c;

        public CutEdit(Coupe c) {
            this.c = c;
        }

        public void undo() {
            c.undo();
        }

        public void redo() {
            c.doit();
        }
    }
}