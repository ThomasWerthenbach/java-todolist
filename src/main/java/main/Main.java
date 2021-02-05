package main;

import controller.HomeController;
import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import util.User;

/**
 * Is the main class of the TodoList Application.
 * Will launch the JavaFX application from here.
 *
 * @author Thomas Werthenbach
 */
public class Main extends Application {
    /**
     * The SceneManager, which keeps track of all the Scene's displayed on the screen.
     */
    public static SceneManager scManager;

    /**
     * Will be called when the application is launched and will launch the JavaFX application.
     * @param args given launch-arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Will be called when the JavaFX application launches.
     * It creates the SceneManager and sets the first Scene depending on whether the User has already stored a name in
     * the database.
     * @param stage the primary stage (window) which will be displayed on the screen.
     */
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
