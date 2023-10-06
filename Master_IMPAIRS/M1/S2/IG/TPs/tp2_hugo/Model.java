import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Model extends BufferedImage implements Observable {

    private ArrayList<Observer> obs = new ArrayList<Observer>();

    private Color color;

    public Model(int sizeX, int sizeY) {
        super(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
        color = Color.BLACK;
        clear();
    }

    @Override
    public void subscribe(Observer obs) {
        this.obs.add(obs);
    }

    @Override
    public void notifyObserver() {
        for (Observer o : obs) {
            o.update(this);
        }
    }

    public void drawLine(int x0, int y0, int x1, int y1) {
        Graphics2D g = this.createGraphics();
        g.setColor(color);
        g.drawLine(x0, y0, x1, y1);
        notifyObserver();
    }

    public void drawCircle(int x0, int y0, int x1, int y1) {
        Graphics2D g = this.createGraphics();
        g.setColor(color);
        g.fillOval(x0, y0,(x1 - x0),(x1 - x0));
        notifyObserver();
    }

    public void paintPixel(int x, int y) {
        Graphics2D g = this.createGraphics();
        g.setColor(color);
        g.fillOval(x, y, 2, 2);
        notifyObserver();
    }

    public void clear() {
        Graphics2D g = this.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 600);
        notifyObserver();
    }

    public void setColor(Color c) {
        color = c;
    }

    public Color getColor() {
        return color;
    }
}