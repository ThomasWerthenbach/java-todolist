package controller.subitems;

import controller.Controller;
import controller.HomeController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import util.Todo;

import static main.Main.scManager;

/**
 * Controls the functionality belonging to the popup on the home screen which users can interact with to add new
 * todoitems.
 *
 * @author Thomas Werthenbach
 */
public class AddPopupController implements Controller {
    /**
     * This method is called when the scene is shown.
     *
     * NOTE: This should not be triggered, as this controller should never be the main scene.
     */
    @Override
    public void onShowScene() {
    }

    /**
     * The field in which the user can add the description of the todoitem.
     */
    @FXML
    TextField descriptionTextField;

    /**
     * The field in which the user can add the deadline of the todoitem.
     */
    @FXML
    DatePicker deadlineDatePicker;

    /**
     * Invoked when the user tries to add a todoitem. It makes sure the todoitem is in line with the specified
     * requirements and should return feedback to the user in such cases. It will also create a todoitem which is to be
     * stored in the database and displayed on the screen. It passes the created todoitem to the HomeController to be
     * processed.
     */
    public void AddTodoPopupButtonClicked() {
        if (this.descriptionTextField.getText().trim().isEmpty()) {
            this.descriptionTextField.setStyle("-fx-background-color: rgba(255, 171, 171, 1)");
            return;
        }

        Todo todo = new Todo();
        todo.setDescription(descriptionTextField.getText().trim());

        if (this.deadlineDatePicker.getValue() != null)
            todo.setDeadline(this.deadlineDatePicker.getValue());

        Controller controller = scManager.getController();
        if (controller instanceof HomeController)
            ((HomeController) controller).addTodo(todo);
        else
            throw new RuntimeException("Only the HomeController is allowed to add new todo's");
    }
}
