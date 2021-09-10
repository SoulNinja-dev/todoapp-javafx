package todo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class HomeController {
    @FXML
    private TextField addTodoField;

    @FXML
    private FlowPane todoList;

    @FXML
    public void onAvatarClick(ActionEvent event) {
        System.out.println("avatar was clicked");
    }

    @FXML
    public void onMenuClick(ActionEvent event) {
        System.out.println("menu was clicked");
    }

    @FXML
    public void onAddTodoClick(ActionEvent event) throws IOException {
        String todoTitle = addTodoField.getText();
        // TODO: verify it's not null

        HBox todoCard = FXMLLoader.load(getClass().getResource("../views/todocard.fxml"));
        Label oldLabel = (Label) todoCard.getChildren().get(1);

        oldLabel.setText(todoTitle);
        todoCard.getChildren().set(1,oldLabel);
        todoList.getChildren().add(todoCard);
    }
}
