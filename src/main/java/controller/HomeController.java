package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.Main;
import org.w3c.dom.Text;
import util.Todo;
import util.TodoListItem;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import java.io.IOException;

import static main.Main.scManager;

/**
 * Controls all functionality related to graphical elements on the HomeScreen (screen displaying the todo-items).
 *
 * @author Thomas Werthenbach
 */
public class HomeController implements Controller {

    /**
     * The popup panel using which new todo-items can be created.
     */
    @FXML
    public Pane AddTodoPane;

    /**
     * The path to the FXML file containing the graphical elements.
     */
    public static final String FXML = "scene/home.fxml";

    /**
     * Is a reference to the "addButton"-ImageView in the FXML file.
     */
    @FXML
    private ImageView addButton;

    /**
     * Is a reference to the "todoList"-ListView in the FXML file.
     */
    @FXML
    public ListView<TodoListItem> todoList;

    /**
     * This method is called when the scene, belonging to this controller, is shown.
     * It loads the FXML file for the popup in which the user can add todo items.
     */
    @Override
    public void onShowScene() {
        Pane newPane;
        try {
            newPane = FXMLLoader.load(Main.class.getResource("subitems/AddPopup.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new NullPointerException("Could not load pane.");
        }
        AddTodoPane.getChildren().add(newPane);
    }

    /**
     * Is triggered when the user moves the mouse such that it "entered" the area of the screen filled by a specific
     * graphical element.
     */
    public void mouseEnterAddButton() {
        scManager.setCursor(Cursor.HAND);
    }

    /**
     * Is triggered when the user moves the mouse such that it "exited" the area of the screen filled by a specific
     * graphical element.
     */
    public void mouseExitAddButton() {
        scManager.setCursor(Cursor.DEFAULT);
    }

    /**
     * Is triggered when the user releases the left-mouse-button when it is being hold while hovering over a specific
     * graphical element.
     */
    public void mouseRelease() {
    } //TODO Hover effect?

    /**
     * Is triggered when the user presses the left-mouse-button while hovering over a specific graphical element.
     */
    public void mousePress() {
        AddTodoPane.setVisible(!AddTodoPane.isVisible());
        if (!AddTodoPane.isVisible())
            AddTodoPane.lookup("#descriptionTextField").setStyle("");
    }

    /**
     * Creates a todoitem. It will store it in the database and put it in the listview on the scene.
     * @param todo is the todoitem to be stored.
     */
    public void addTodo(Todo todo) {
        int id = todo.storeTodo();
        TodoListItem item = new TodoListItem(id, todo);
        todoList.getItems().add(item);
    }
}
