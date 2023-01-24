import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
    private JMenuItem quitter;

    public MenuBar() {
        this.quitter = new JMenuItem("Quitter");
        this.add(quitter);
    }

    public JMenuItem getQuitter() {
        return quitter;
    }
}
 