package todo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class TodoController {

    @FXML
    private HBox todoItem;

    @FXML
    public void onTodoDone(ActionEvent event) {
        FlowPane root = (FlowPane) todoItem.getParent();
        root.getChildren().remove(todoItem);
    }

}
