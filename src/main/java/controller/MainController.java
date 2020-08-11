package controller;

import database.ExecQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static main.Main.scManager;

public class MainController {
    public static final String FXML = "scene/main.fxml";

    @FXML
    private TextField NameField;

    public void pressStartButton(ActionEvent action) {
        String name = NameField.getText();

        ExecQuery query = new ExecQuery();
        try {
            query
                    .Prepare(ExecQuery.Query.STORE_NAME)
                    .setParam("name")
                    .setParam(name)
                    .execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scManager.setScene(HomeController.FXML);
    }
}
