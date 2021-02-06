package controller;

import javafx.scene.input.KeyCode;
import org.testfx.framework.junit.ApplicationTest;

public class BaseApplicationTest extends ApplicationTest {
    public void keyBoardType(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '/')
                type(KeyCode.SLASH);
            else
                type(KeyCode.getKeyCode("" + text.charAt(i)));
        }
    }
}
