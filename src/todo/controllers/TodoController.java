package todo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import todo.models.TodoModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


public class TodoController {

    @FXML
    private HBox todoItem;

    @FXML
    private Label todoTitle;

    @FXML
    public void onTodoDone(ActionEvent event) throws SQLException, IOException {
        FlowPane root = (FlowPane) todoItem.getParent();
        TodoModel.getInstance().deleteTodo(todoTitle.getText());
        root.getChildren().remove(todoItem);

        if((long) root.getChildren().size() == 0) {
            Parent label = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../components/nothingTodo.fxml")));
            root.getChildren().add(label);
        }
    }

}
