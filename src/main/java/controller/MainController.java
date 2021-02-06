package controller;

import database.ExecQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.User;

import java.sql.SQLException;

import static main.Main.scManager;

/**
 * Controls all functionality related to graphical elements on the MainScreen (screen that is being displayed on initial
 * start-up of the application).
 *
 * @author Thomas Werthenbach
 */
public class MainController implements Controller {

    /**
     * The path to the FXML file containing the graphical elements.
     */
    public static final String FXML = "scene/main.fxml";

    /**
     * Is a reference to the "NameField"-TextField in the FXML file.
     */
    @FXML
    private TextField NameField;

    /**
     * This method is called when the scene, belonging to this controller, is shown.
     */
    @Override
    public void onShowScene() {
    }

    /**
     * Is triggered when the "Start"-button is pressed.
     * Stores the name of the current user in the database.
     * @param action
     */
    public void pressStartButton(ActionEvent action) {
        String name = NameField.getText();

        ExecQuery query = new ExecQuery();
        try {
            query
                    .prepare(ExecQuery.Query.STORE_KEY)
                    .setParam("name")
                    .setParam(name)
                    .execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User.name = name; //TODO user should not go to home screen when database connection failed

        scManager.setScene(HomeController.FXML);
    }
}
