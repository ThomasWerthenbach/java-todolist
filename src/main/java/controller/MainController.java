package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static main.Main.scManager;

public class MainController {
    public static final String FXML = "scene/main.fxml";

    @FXML
    private TextField NameField;

    public void pressStartButton(ActionEvent action) {
        String name = NameField.getText();

        //TODO: Store name in local SQLite database.

        scManager.setScene(HomeController.FXML);
    }
}
