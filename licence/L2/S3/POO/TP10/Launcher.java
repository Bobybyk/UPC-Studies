import java.io.IOException;
import java.awt.EventQueue;
public class Launcher {

    public static void main(String[] args) throws IOException {
        try {
            ImageEditModel model = new ImageEditModel("landscape.png");
            ImageEditView image = new ImageEditView(model);
        
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    image.pack();
                    image.setVisible(true);
                }
            });
        } catch (IOException e) {
            System.out.println("Aucun fichier trouvé");
        }
    }

}
