package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApplication extends Application {
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
		primaryStage.setTitle("Игра Города");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 600, 250));
		primaryStage.show();
		stage = primaryStage;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void main(String[] args) {
        UploadCities thread = new UploadCities();
        thread.start();
		launch(args);
	}
}
