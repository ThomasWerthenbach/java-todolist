package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import main.Main;

import java.io.IOException;

/**
 * Symbolises an item in the list of todo's on the main screen.
 *
 * @author Thomas Werthenbach
 */
public class TodoListItem extends ListCell<Todo> {
    /**
     * Is the main container of the ListItem. It includes all other graphical elements.
     */
    private final HBox l;

    /**
     * Is the id of the current ListItem.
     */
    private final int id;

    /**
     * Is the constructor of TodoListItem.
     * Will load the graphical elements from the FXML files into the HBox.
     * @param id the id of the current TodoListItem.
     */
    public TodoListItem(int id) {
        this.id = id;
        try {
            l = FXMLLoader.load(Main.class.getResource("subitems/TodoListItem.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setGraphic(l);
        Button button = (Button) l.lookup("#button");
        button.setOnMouseClicked(mouseEvent -> print());
        button.setId("button_" + id);
    }

    /**
     * Prints the id of the current TodoListItem in the console.
     */
    public void print() {
        System.out.println(this.id);
    }
}
