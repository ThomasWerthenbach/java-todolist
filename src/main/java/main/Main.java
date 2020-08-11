package main;

import controller.HomeController;
import controller.MainController;
import database.ExecQuery;
import javafx.application.Application;
import javafx.stage.Stage;
import util.User;

import java.sql.SQLException;

public class Main extends Application {
    public static SceneManager scManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        scManager = new SceneManager();

        scManager.setStage(stage).setResizable(false);

        if (User.loadName() == null)
            scManager.setScene(MainController.FXML);
        else
            scManager.setScene(HomeController.FXML);

        scManager.show();
    }
}
