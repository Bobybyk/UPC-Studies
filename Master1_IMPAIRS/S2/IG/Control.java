import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Control {
    public Control(Window view) {
        view.getMyMenuBar().getQuitter().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        view.getMyMenuBar().getQuitter().addActionListener(event -> {
            System.exit(0);
        });
    }
}
