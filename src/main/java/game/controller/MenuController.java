package game.controller;

import game.GameApplication;
import game.model.Game;
import game.model.Person;
import game.model.base.PersonStrategy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public final class MenuController implements Initializable {

    private static Game game;

    private int strategy = 1;
    private int strategy2 = 3;

    //buttons
    @FXML
    private Button btn_onStart;
    @FXML
    private Button onExit;
    @FXML
    private MenuButton mbtn_quality_left;
    @FXML
    private MenuButton mbtn_quality_right;

    //name players
    @FXML
    private Label name_left;
    @FXML
    private Label name_right;

    //quality
    @FXML
    private Label txt_qualityleft;
    @FXML
    private Label txt_qualityright;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (game == null) {
            game = Game.createGame(name_left.getText(), name_right.getText());
            System.out.println("Create game");
        }
        System.out.println("Menu");
        validateApplication();
    }

    @FXML
    public void pressOnStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));
        Stage primaryStage = GameApplication.getStage();
        primaryStage.setScene(new Scene(root, 600, 250));
    }


    @FXML
    public void pressName(MouseEvent event) {
        Label label = (Label) event.getSource();
        TextInputDialog dialog = new TextInputDialog(label.getText());
        dialog.setTitle("Смена никнейма");
        dialog.setHeaderText(null);
        dialog.setContentText("Никнейм:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(label::setText);

    }

    @FXML
    public void setStrategy(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();

    }

    @FXML
    public void setQuality(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();

    }

    private void validateApplication() {
        validateNamePlayers();
        validateGameQuality();
        validateVisible();
    }

    private void validateNamePlayers() {
        if (!game.getLeftPerson().getName().equals(name_left.getText())) {
            name_left.setText(game.getLeftPerson().getName());
        }
        if (!game.getRightPerson().getName().equals(name_right.getText())) {
            name_right.setText(game.getRightPerson().getName());
        }
    }

    private void validateGameQuality() {
        updateQuality(game.getLeftPerson(), txt_qualityleft);
        updateQuality(game.getRightPerson(), txt_qualityright);

    }

    private void validateVisible() {
        updateVisible(game.getLeftPerson(), mbtn_quality_left, txt_qualityleft);
        updateVisible(game.getRightPerson(), mbtn_quality_right, txt_qualityright);
    }

    private void updateVisible(Person person, MenuButton button, Label label) {
        if (person.getStrategy() != PersonStrategy.FIRST) {
            button.setVisible(true);
            label.setVisible(true);
        } else {
            button.setVisible(false);
            label.setVisible(false);
        }
    }

    private void updateQuality(Person person, Label label) {
        switch (person.getQuality()) {
            case EASY: {
                label.setText("Лёгкий");
                label.setTextFill(Color.GREEN);
                break;
            }
            case NORMAL: {
                label.setText("Средний");
                label.setTextFill(Color.YELLOW);
                break;
            }
            case HARD: {
                label.setText("Сложный");
                label.setTextFill(Color.RED);
                break;
            }
        }
    }

}
