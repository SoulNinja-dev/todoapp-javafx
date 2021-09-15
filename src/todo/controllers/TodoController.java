package todo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import todo.models.TodoModel;

import java.sql.SQLException;


public class TodoController {

    @FXML
    private HBox todoItem;

    @FXML
    private Label todoTitle;

    @FXML
    public void onTodoDone(ActionEvent event) throws SQLException {
        FlowPane root = (FlowPane) todoItem.getParent();
        TodoModel.getInstance().deleteTodo(todoTitle.getText());
        root.getChildren().remove(todoItem);
    }
}
