package game.controller;

import game.GameApplication;
import game.ai.AiThread;
import game.model.City;
import game.model.Game;
import game.model.Person;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static game.controller.MenuController.getGame;

public final class GameController implements Initializable {
    private Game game;

    //name players
    @FXML
    private Label name_left;
    @FXML
    private Label name_right;
    //winner label
    @FXML
    private Label winner_left;
    @FXML
    private Label winner_right;
    //time label
    @FXML
    private Label time;

    @FXML
    private ListView listview_left;
    @FXML
    private ListView listview_right;

    private Thread threadGame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = getGame();
        System.out.println("Game");
        validateApplication();
        game.start();
        threadGame = new Thread(() -> {
            try {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        syncListView();
                        syncTime();
                    }
                };
                while (!game.getFinished()) {
                    Thread.sleep(500);
                    Platform.runLater(updater);
                }
                if (game.getWinner() != null) {
                    selectWinner(game.getWinner());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        AiThread aiThread = new AiThread(game);
        threadGame.start();
        aiThread.start();
    }

    @FXML
    public void pressOnExit(ActionEvent event) throws IOException {
        game.stop();
        try {
            threadGame.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        Stage primaryStage = GameApplication.getStage();
        primaryStage.setScene(new Scene(root, 600, 250));
    }

    private void validateApplication() {
        validateNamePlayers();
        syncListView();
    }

    private void validateNamePlayers() {
        if (!game.getLeftPerson().getName().equals(name_left.getText())) {
            name_left.setText(game.getLeftPerson().getName());
        }
        if (!game.getRightPerson().getName().equals(name_right.getText())) {
            name_right.setText(game.getRightPerson().getName());
        }
    }

    private void syncListView() {
        //listview_left.getItems().clear();
        List<String> list = new ArrayList<>();
        if (game.getUsedByLeftPerson() != null) {
            Map<Integer, City> temp = new TreeMap<>(game.getUsedByLeftPerson());
            for (Map.Entry<Integer, City> entry : temp.entrySet()) {
                list.add(entry.getKey() + " " + entry.getValue());
            }
        }
        listview_left.getItems().setAll(list);
        List<String> list2 = new ArrayList<>();
        if (game.getUsedByRightPerson() != null) {
            Map<Integer, City> temp = new TreeMap<>(game.getUsedByRightPerson());
            for (Map.Entry<Integer, City> entry : temp.entrySet()) {
                list2.add(entry.getKey() + " " + entry.getValue());
            }
        }
        // listview_right.getItems().clear();
        listview_right.getItems().setAll(list2);
    }

    private void syncTime() {
        int seconds = game.getGameTime();
        int min = 0;
        while (seconds > 60) {
            min++;
            seconds -= 60;
        }
        time.setText(String.format("%02d:%02d", min, seconds));
    }

    private void selectWinner(Person person) {
        switch (person.getSwitchPerson()) {
            case LEFT: {
                winner_left.setVisible(true);
                winner_left.setTextFill(Color.GREEN);
                break;
            }
            case RIGHT: {
                winner_right.setVisible(true);
                winner_right.setTextFill(Color.GREEN);
                break;
            }
        }
    }

}
