package controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import main.Main;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static main.Main.scManager;
import static org.assertj.core.api.Assertions.assertThat;

public class MainControllerTest extends ApplicationTest {
    @Before
    public void BeforeClass() throws Exception {
        ApplicationTest.launch(Main.class);
    }

    @Test
    public void pressStartButton() {
        Scene sc = scManager.getScene();
        clickOn("#NameField");
        String text = "TEST";
        for (int i = 0; i < text.length(); i++) {
            type(KeyCode.getKeyCode("" + text.charAt(i)));
        }
        clickOn("#btn");
        assertThat(scManager.getScene()).isNotEqualTo(sc);
    }
}
