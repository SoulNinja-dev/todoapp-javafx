package todo.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TodoController {

    @FXML
    private Label todoTitle;

    @FXML
    private HBox todoItem;

    public HBox generateCard(String title) {
        todoTitle.setText(title);
        return todoItem;
    }

}
