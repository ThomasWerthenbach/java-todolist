package controller.subitems;

import controller.BaseApplicationTest;
import controller.HomeController;
import database.DatabaseConnection;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static main.Main.scManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddPopupControllerTest extends BaseApplicationTest {

    @Before
    public void before() throws Exception {
        ResultSet rs = mock(ResultSet.class);
        when(rs.getInt("rowcount")).thenReturn(1);
        when(rs.getString("name")).thenReturn("testname");

        PreparedStatement prep = mock(PreparedStatement.class);
        when(prep.executeQuery()).thenReturn(rs);

        Connection mockConn = mock(Connection.class);
        when(mockConn.prepareStatement(Mockito.anyString())).thenReturn(prep);
        DatabaseConnection.setConn(mockConn);
    }

    @Test
    public void AddTodoPopupButtonClickedFailTest() throws Exception {
        // Execution
        ApplicationTest.launch(Main.class);
        clickOn("#addButton");
        clickOn("#AddTodoPopupButton");

        HomeController controller = (HomeController) scManager.getController();
        TextField field = (TextField) controller.AddTodoPane.lookup("#descriptionTextField");
        assertThat(field.getStyle()).contains("-fx-background-color");
    }

    @Test
    public void AddTodoPopupButtonClickedSucceedTest() throws Exception {
        // Execution
        ApplicationTest.launch(Main.class);
        clickOn("#addButton");
        clickOn("#descriptionTextField");
        keyBoardType("TEST");
        clickOn("#deadlineDatePicker");
        keyBoardType("01/01/2021");
        clickOn("#AddTodoPopupButton");

        HomeController controller = (HomeController) scManager.getController();
        TextField field = (TextField) controller.AddTodoPane.lookup("#descriptionTextField");
        assertThat(field.getStyle()).isEmpty();
        assertThat(controller.todoList.getItems().size()).isGreaterThan(0);
        //TODO check database activity
    }

    @Test
    public void AddTodoPopupButtonClickedSucceedWithoutDateTest() throws Exception {
        // Execution
        ApplicationTest.launch(Main.class);
        clickOn("#addButton");
        clickOn("#descriptionTextField");
        keyBoardType("TEST");
        clickOn("#AddTodoPopupButton");

        HomeController controller = (HomeController) scManager.getController();
        TextField field = (TextField) controller.AddTodoPane.lookup("#descriptionTextField");
        assertThat(field.getStyle()).isEmpty();
        assertThat(controller.todoList.getItems().size()).isGreaterThan(0);
        //TODO check database activity
    }
}