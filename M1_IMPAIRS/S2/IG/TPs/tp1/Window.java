import javax.swing.JFrame;

public class Window extends JFrame {
    private TabPane tabs;
    private MenuBar menuBar;

    public Window() {
        super("My Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        tabs = new TabPane();
        menuBar = new MenuBar();

        this.getContentPane().add(tabs);     
        this.setJMenuBar(menuBar); 

        setVisible(true);
    }

    public TabPane getMyTabs() {
        return tabs;
    }
    public MenuBar getMyMenuBar() {
        return menuBar;
    }
}