import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class Toolbox extends JPanel {

    private AbstractController currentController;

    public Toolbox(Model m, View v) {

        this.setPreferredSize(new Dimension(150, 600));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.addButton(new LineController("Segment", v, m), true);
        this.addButton(new CircleController("Cercle", v, m), false);
        this.addButton(new PenController("Crayong", v, m), false);

        this.add(new Box.Filler(new Dimension(5, 100), new Dimension(5, 350), new Dimension(Short.MAX_VALUE, 100)));

        this.addButton("Couleur", e -> {
            Color newColor = JColorChooser.showDialog(this,"Choisir la couleur",Color.BLACK);
            m.setColor(newColor);
        });

        this.addButton("Effacer", e -> {
            m.clear();
        });
    }

    private void addButton(String name, ActionListener listener) {
        JButton b = new JButton(name);
        b.setAlignmentX(CENTER_ALIGNMENT);
        b.setPreferredSize(new Dimension(130, 30));
        b.addActionListener(listener);
        this.add(b);
    }

    private void addButton(AbstractController controller, boolean init) {

        if (init) {
            currentController = controller;
            controller.enable();
        }

        JButton b = new JButton(controller.getName());
        b.setAlignmentX(CENTER_ALIGNMENT);
        b.setPreferredSize(new Dimension(130, 30));
        b.addActionListener(e -> {
            currentController.disable();
            controller.enable();
            currentController = controller;
        });

        this.add(b);
    }
}
