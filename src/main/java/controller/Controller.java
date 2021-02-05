package controller;

/**
 * Controllers control the user interface. Controllers can communicate with other classes to perform certain tasks. They
 * determine what tasks the users can execute, but also limits them, as users can not access functionalities that are
 * unavailable to the controllers.
 * These controllers form the base for model-view-controller architecture.
 *
 * @author Thomas Werthenbach
 */
public interface Controller {
    void onShowScene();
}
