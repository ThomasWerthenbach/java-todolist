package controller;

import util.TodoListItem;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static main.Main.scManager;


public class HomeController {
    public static final String FXML = "scene/home.fxml";

    private int counter = 0;

    @FXML
    private ImageView addButton;

    @FXML
    private ListView todoList;

    public void mouseEnterAddButton(MouseEvent event) {
        scManager.setCursor(Cursor.HAND);
    }

    public void mouseExitAddButton(MouseEvent event) {
        scManager.setCursor(Cursor.DEFAULT);
    }

    public void mouseRelease(MouseEvent e) {
        addButton.setVisible(true);
    }

    public void mousePress(MouseEvent actionEvent) {
        todoList.setFocusTraversable(false);
        addButton.setVisible(false);
        todoList.getItems().add(new TodoListItem(counter++));
    }
}
