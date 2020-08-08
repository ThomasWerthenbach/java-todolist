package main;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static SceneManager scManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        scManager = new SceneManager();
        scManager.setStage(stage)
                .setResizable(false)
                .setScene(MainController.FXML)
                .show();
    }
}
