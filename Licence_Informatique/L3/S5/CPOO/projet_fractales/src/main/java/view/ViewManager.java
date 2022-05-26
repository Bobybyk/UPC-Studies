package view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ViewManager {

    public ViewManager() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

}
