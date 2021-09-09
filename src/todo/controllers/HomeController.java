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
        // System.out.println(todoTitle);
        // TODO: verify it's not null
        HBox todoCard = FXMLLoader.load(getClass().getResource("../views/todocard.fxml"));
        Label newLabel = new Label(todoTitle);
        System.out.println(todoCard.getChildren().set(1,newLabel));
        todoList.getChildren().add(todoCard);
        System.out.println(todoList.getChildren());
    }
}
