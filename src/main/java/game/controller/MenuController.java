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

    public static Game getGame() {
        return game;
    }

    //buttons
    @FXML
    private Button btn_onStart;
    @FXML
    private Button onExit;
    @FXML
    private MenuButton mbtn_quality_left;
    @FXML
    private MenuButton mbtn_quality_right;

    //radio buttons
    @FXML
    private RadioButton left_first;
    @FXML
    private RadioButton left_second;
    @FXML
    private RadioButton left_third;
    @FXML
    private RadioButton right_first;
    @FXML
    private RadioButton right_second;
    @FXML
    private RadioButton right_third;


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
        if (result.isPresent()) {
            String[] strings = label.getId().split("_");
            if (strings[1].equals("left")) {
                game.getLeftPerson().setName(result.get());
            } else {
                game.getRightPerson().setName(result.get());
            }
        }
        validateApplication();

    }

    @FXML
    public void setStrategy(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        String[] string = radioButton.getId().split("_");

        if (string[0].equals("left")) {
            updateStrategy(game.getLeftPerson(), string[1]);
        } else {
            updateStrategy(game.getRightPerson(), string[1]);
        }
        validateApplication();

    }

    @FXML
    public void setQuality(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String[] string = menuItem.getId().split("_");

        if (string[0].equals("left")) {
            updatePersonQuality(game.getLeftPerson(), Integer.parseInt(string[1]));
        } else {
            updatePersonQuality(game.getRightPerson(), Integer.parseInt(string[1]));
        }
        validateApplication();

    }

    private void validateApplication() {
        validateNamePlayers();
        validateGameQuality();
        validateVisible();
    }

    private void updateStrategy(Person person, String strategy) {
        switch (strategy) {
            case "first": {
                person.setStrategy(PersonStrategy.FIRST);
                break;
            }
            case "second": {
                person.setStrategy(PersonStrategy.SECOND);
                break;
            }
            case "third": {
                person.setStrategy(PersonStrategy.THIRD);
                break;
            }
        }
    }


    private void updatePersonQuality(Person person, int quality) {
        person.setQuality(quality);
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
        updateRadioButton();
    }

    private void updateRadioButton() {
        switch (game.getLeftPerson().getStrategy()) {
            case FIRST: {
                left_first.setSelected(true);
                break;
            }
            case SECOND: {
                left_second.setSelected(true);
                break;
            }
            case THIRD: {
                left_third.setSelected(true);
                break;
            }
        }
        switch (game.getRightPerson().getStrategy()) {
            case FIRST: {
                right_first.setSelected(true);
                break;
            }
            case SECOND: {
                right_second.setSelected(true);
                break;
            }
            case THIRD: {
                right_third.setSelected(true);
                break;
            }
        }
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
        int quality = person.getQuality();
        if (quality <= 30) {
            label.setText("Лёгкий");
            label.setTextFill(Color.GREEN);
        } else if (quality <= 70) {
            label.setText("Средний");
            label.setTextFill(Color.YELLOWGREEN);
        } else {
            label.setText("Сложный");
            label.setTextFill(Color.RED);
        }
    }

}
