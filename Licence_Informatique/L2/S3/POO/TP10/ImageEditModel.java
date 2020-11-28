import java.awt.image.*;
import java.awt.Rectangle;
import java.awt.Color;
import java.io.*;
import javax.imageio.*;

public class ImageEditModel {
    private BufferedImage image;

    public ImageEditModel(String path) throws IOException {
        try {
            this.image = ImageIO.read(new File(path));
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void fillzone(Rectangle z, int [][] pixels) {
        for(int i = 0 ; i < (int) z.getWidth() ; i++) {
            for (int j = 0 ; j < (int) z.getHeight() ; j++) {
                this.image.setRGB(i+Math.abs((int) z.getX()), j+Math.abs((int) z.getY()), pixels[i][j]);
            }
        }
    }

    public void clearzone (Rectangle z) {
        fillzone(z, getColor(z, Color.white));
    }

    public int[][] getColor(Rectangle z, Color color) {
        return new int[(int) z.getWidth()][(int) z.getHeight()];
    }

    public BufferedImage getImage() {
        return this.image;
    }
}