package game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutMe {
    public static void openWindow() throws IOException {
        Stage newWindow = new Stage();
        Parent root = FXMLLoader.load(AboutMe.class.getResource("/fxml/aboutMe.fxml"));
        newWindow.setTitle("Об Авторе");
        newWindow.setScene(new Scene(root, 358, 400));
        newWindow.initModality(Modality.WINDOW_MODAL);
        Stage primaryStage = GameApplication.getStage();
        newWindow.initOwner(primaryStage);
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);
        newWindow.setResizable(false);
        newWindow.show();
    }
}
