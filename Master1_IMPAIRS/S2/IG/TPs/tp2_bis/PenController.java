import java.awt.event.MouseEvent;

public class PenController extends AbstractController{

    public PenController(String name, View v, Model m) {
        super(name, v, m);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        m.paintPixel(e.getX(), e.getY());
    }
}
