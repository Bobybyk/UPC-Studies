package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import model.Graph;
import model.Parser;

public class Window extends JFrame {

    private JPanel graphPanel;

    public Window(int width, int height) {
        this.setTitle("Graph");
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        graphPanel = new JPanel();
        graphPanel.setLayout(new BorderLayout());
        this.getContentPane().add(graphPanel);

        this.setVisible(true);
    }

    public void drawGraph(Graph g) {
        for (int i = 0; i < g.getVertices().size(); i++) {
            Circle circle = new Circle(g.getVertices().get(i).getValue() + "", Parser.getVerticesCoords().get(i)[0], Parser.getVerticesCoords().get(i)[1]);
            circle.setBounds(Parser.getVerticesCoords().get(i)[0], Parser.getVerticesCoords().get(i)[1], 40, 40);
            graphPanel.add(circle);
            System.out.println(graphPanel.getComponentCount());
        }
    }
}
