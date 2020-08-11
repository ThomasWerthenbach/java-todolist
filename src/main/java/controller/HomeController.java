package controller;

import util.TodoListItem;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static main.Main.scManager;

/**
 * Controls all functionality related to graphical elements on the HomeScreen (screen displaying the todo-items).
 *
 * @Author Thomas Werthenbach
 */
public class HomeController {
    /**
     * The path to the FXML file containing the graphical elements.
     */
    public static final String FXML = "scene/home.fxml";

    /**
     * Keeps track of the number of created todo's.
     */
    private int counter = 0;

    /**
     * Is a reference to the "addButton"-ImageView in the FXML file.
     */
    @FXML
    private ImageView addButton;

    /**
     * Is a reference to the "todoList"-ListView in the FXML file.
     */
    @FXML
    private ListView<TodoListItem> todoList;

    /**
     * Is triggered when the user moves the mouse such that it "entered" the area of the screen filled by a specific
     * graphical element.
     * @param event
     */
    public void mouseEnterAddButton(MouseEvent event) {
        scManager.setCursor(Cursor.HAND);
    }

    /**
     * Is triggered when the user moves the mouse such that it "exited" the area of the screen filled by a specific
     * graphical element.
     * @param event
     */
    public void mouseExitAddButton(MouseEvent event) {
        scManager.setCursor(Cursor.DEFAULT);
    }

    /**
     * Is triggered when the user releases the left-mouse-button when it is being hold while hovering over a specific
     * graphical element.
     * @param e
     */
    public void mouseRelease(MouseEvent e) {
        addButton.setVisible(true);
    }

    /**
     * Is triggered when the user presses the left-mouse-button while hovering over a specific graphical element.
     * @param actionEvent
     */
    public void mousePress(MouseEvent actionEvent) {
        todoList.setFocusTraversable(false);
        addButton.setVisible(false);
        todoList.getItems().add(new TodoListItem(counter++));
    }
}
