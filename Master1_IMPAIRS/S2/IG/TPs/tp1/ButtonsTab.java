import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsTab extends JPanel {
    
    public ButtonsTab(String name) {
        this.setName(name);
        this.add(new JButton("bouton 1"));
    }
}
