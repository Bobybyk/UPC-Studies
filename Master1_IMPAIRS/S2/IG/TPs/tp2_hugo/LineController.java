import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class LineController extends AbstractController{

    private int x0, y0;

    public LineController(String name, View v, Model m) {
        super(name, v, m);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x0 = e.getX();
        y0 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        m.drawLine(x0, y0, e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        v.repaint();
        Graphics g = v.getGraphics();

        g.setColor(m.getColor());
        g.setXORMode(Color.CYAN);
        g.drawLine(x0, y0, e.getX(), e.getY());
    }
}
