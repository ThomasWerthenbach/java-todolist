package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import main.Main;

import java.io.IOException;

public class MainController implements Controller {
    @FXML
    private Button btn;

    public void switchTo() {
        Parent root;
        try {
            root = FXMLLoader.load(MainController.class.getResource("main.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
    }

    public void pressStartButton(ActionEvent action) {

    }
}
