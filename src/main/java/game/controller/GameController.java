package game.controller;

import game.GameApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Game");
    }

    @FXML
    public void pressOnExit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        Stage primaryStage = GameApplication.getStage();
        primaryStage.setScene(new Scene(root, 600, 250));
    }

}
