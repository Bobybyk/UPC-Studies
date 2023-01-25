package view;

import javax.swing.JFrame;

public class Window extends JFrame {
    private Slate slate;
    private Tools tools;

    public Window() {
        super("My Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        this.slate = new Slate();
        this.tools = new Tools();
        add(this.slate);
        add(this.tools);
        setVisible(true);
    }

    public Slate getSlate() {
        return slate;
    }
    public Tools getTools() {
        return tools;
    }
}