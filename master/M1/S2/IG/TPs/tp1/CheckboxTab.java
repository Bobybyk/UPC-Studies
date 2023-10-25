import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckboxTab extends JPanel {
    
    public CheckboxTab(String name) {
        this.setName(name);
        this.add(new JCheckBox("check 1"));
    }
}
