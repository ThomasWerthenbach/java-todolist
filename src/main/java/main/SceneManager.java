package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private Stage stage;

    public SceneManager setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public SceneManager setResizable(boolean resizable) {
        this.stage.setResizable(resizable);
        return this;
    }

    public SceneManager setScene(String fileName) {
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

    public void show() {
        this.stage.show();
    }

    public void setCursor(Cursor cursor) {
        this.stage.getScene().setCursor(cursor);
    }
}
