import javax.swing.JTabbedPane;

public class TabPane extends JTabbedPane {
    public TabPane() {
        super();
        ButtonsTab tab1 = new ButtonsTab("buttons tab");
        CheckboxTab tab2 = new CheckboxTab("checkbox tab");
        this.addTab(tab1.getClass() + ", " + tab1.getName(), tab1);
        this.addTab(tab2.getClass() + ", " + tab2.getName(), tab2);
    }
}
