package Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import main.Main;

import java.io.IOException;

public class TodoListItem extends ListCell<Todo> {
    private final HBox l;
    private final int id;

    public TodoListItem(int id) {
        this.id = id;
        try {
            l = FXMLLoader.load(Main.class.getResource("scene/TodoListItem.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setGraphic(l);
        Button button = (Button) l.lookup("#button");
        button.setOnMouseClicked(mouseEvent -> print());
        button.setId("button_" + id);
    }

    public void print() {
        System.out.println(this.id);
    }
}
