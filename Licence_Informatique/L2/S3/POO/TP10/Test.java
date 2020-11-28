import java.io.IOException;
import java.awt.Rectangle;

public class Test {

    public static void main(String[] args) throws IOException {
        ImageEditModel model = new ImageEditModel("landscape.png");
        Rectangle rec = new Rectangle (100, 100, 100, 100);
        model.fillzone(rec, new int[100][100]);
    }
}
