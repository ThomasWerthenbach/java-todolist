package controller;

import database.DatabaseConnection;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import main.Main;
import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;
import util.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static main.Main.scManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MainControllerTest extends ApplicationTest {
    public void keyBoardType(String text) {
        for (int i = 0; i < text.length(); i++) {
            type(KeyCode.getKeyCode("" + text.charAt(i)));
        }
    }

    @Test
    public void pressStartButtonTest() throws Exception {
        // Preparation
        ResultSet rs = mock(ResultSet.class);
        when(rs.getInt("rowcount")).thenReturn(0);

        PreparedStatement prep = mock(PreparedStatement.class);
        when(prep.executeQuery()).thenReturn(rs);

        Connection mockConn = mock(Connection.class);
        when(mockConn.prepareStatement(Mockito.anyString())).thenReturn(prep);
        DatabaseConnection.setConn(mockConn);

        // Execution
        ApplicationTest.launch(Main.class);
        Scene sc = scManager.getScene();
        clickOn("#NameField");
        keyBoardType("TESTNAME");
        clickOn("#btn");

        // Verification
        assertThat(scManager.getScene()).isNotEqualTo(sc);
        assertThat(User.name).isEqualTo("testname");

        verify(prep, times(1)).execute();
        verify(prep, times(1)).executeQuery();
        verify(prep, times(1)).setString(1, "name");
        verify(prep, times(1)).setString(2, "testname");
        verify(mockConn, times(2)).prepareStatement(Mockito.anyString());
    }

    @Test
    public void alreadyLoggedInTest() throws Exception {
        // Preparation
        ResultSet rs = mock(ResultSet.class);
        when(rs.getInt("rowcount")).thenReturn(1);
        when(rs.getString("name")).thenReturn("testname");

        PreparedStatement prep = mock(PreparedStatement.class);
        when(prep.executeQuery()).thenReturn(rs);

        Connection mockConn = mock(Connection.class);
        when(mockConn.prepareStatement(Mockito.anyString())).thenReturn(prep);
        DatabaseConnection.setConn(mockConn);

        // Execution
        ApplicationTest.launch(Main.class);

        // Verification
        assertThat(scManager.getCurrentFileName()).isEqualTo("scene/home.fxml");
        assertThat(User.name).isEqualTo("testname");

        verify(prep, never()).execute();
        verify(prep, times(1)).executeQuery();
        verify(mockConn, times(1)).prepareStatement(Mockito.anyString());
    }
}
