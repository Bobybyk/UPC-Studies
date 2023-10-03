import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class MyGIMP extends JFrame{
    
    public MyGIMP(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Model m = new Model(600, 600);
        View v = new View();
        Toolbox t = new Toolbox(m, v);

        Container panel = this.getContentPane();

        panel.setLayout(new FlowLayout());
        panel.add(v);
        panel.add(t);

        m.subscribe(v);
        m.notifyObserver();

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

}
