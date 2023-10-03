import java.awt.event.MouseAdapter;

public abstract class AbstractController extends MouseAdapter{

    protected View v;
    private String name;
    protected Model m;

    public AbstractController(String name, View v, Model m) {
        this.v = v;
        this.name = name;
        this.m = m;
    }

    public void enable() {
        v.addMouseListener(this);
        v.addMouseMotionListener(this);
    }

    public void disable() {
        v.removeMouseListener(this);
        v.removeMouseMotionListener(this);
    }

    public String getName() {
        return this.name;
    }
}