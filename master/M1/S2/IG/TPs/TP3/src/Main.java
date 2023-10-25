import controller.Control;
import model.Parser;
import view.Window;

public class Main {
    public static void main(String[] args) {
        Parser.parseXY();
        int width = Parser.getWindowWidth();
        int height = Parser.getWindowHeight();
        new Control(new Window(width, height));
    }
}