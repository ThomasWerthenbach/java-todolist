package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages all which Scene is being displayed on the screen.
 * Makes use of the "builder"-design pattern to ease development processes and improve code styling.
 *
 * @Author Thomas Werthenbach
 */
public class SceneManager {
    /**
     * The current stage (window) that is being displayed on the screen.
     */
    private Stage stage = null;

    /**
     * The filename of the file containing the FXML for the current Scene.
     */
    private String currentFileName = null;

    /**
     * Sets the stage of the SceneManager.
     * @param stage is the new Stage.
     * @return the current SceneManager. (See the "Builder"-design pattern)
     */
    public SceneManager setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    /**
     * Sets the Resizable attribute of the current stage.
     * @param resizable the new value of resizable.
     * @return the current SceneManager. (See the "Builder"-design pattern)
     */
    public SceneManager setResizable(boolean resizable) {
        if (this.stage == null)
            throw new NullPointerException("Stage was not yet set.");
        this.stage.setResizable(resizable);
        return this;
    }

    /**
     * Sets the Scene of the current stage.
     * @param fileName of the FXML file of the new Scene.
     * @return the current SceneManager. (See the "Builder"-design pattern)
     */
    public SceneManager setScene(String fileName) {
        this.currentFileName = fileName;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        return this;
    }

    /**
     * Returns the current Scene.
     * @return the current Scene.
     */
    public Scene getScene() {
        if (this.stage == null)
            throw new NullPointerException("Stage was not yet set.");
        return this.stage.getScene();
    }

    /**
     * Will show the stage on the screen.
     */
    public void show() {
        if (this.stage == null)
            throw new NullPointerException("Stage was not yet set.");
        this.stage.show();
    }

    /**
     * Will change the cursor to a different type.
     * @param cursor the new cursor type.
     */
    public void setCursor(Cursor cursor) {
        this.stage.getScene().setCursor(cursor);
    }

    /**
     * Gets the filename of the FXML of the current scene.
     */
    public String getCurrentFileName() {
        return this.currentFileName;
    }
}
