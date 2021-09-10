package todo.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {

    @FXML
    private TextField addTodoField;

    @FXML
    private VBox rootPane;

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
        addTodo();
    }

    private void addTodo() throws IOException {

        String todoTitle = addTodoField.getText();

        if(todoTitle.isEmpty()) {
            boolean doesErrorExist = false;

            // checks if there's already an errorLabel in the rootpane.
            ObservableList<Node> children = rootPane.getChildren();
            for(Node node : children) {
                if(node.getId() == null) {}
                else if(node.getId().equals("errorLabel")) {
                    doesErrorExist = true;
                    break;
                }
            }
            if(!doesErrorExist) {
                // only add error label when doesErrorExist is false
                Label error = FXMLLoader.load(getClass().getResource("../components/errorLabel.fxml"));
                rootPane.getChildren().add(2, error);
            }
        }
        else {
            // when it's not null, add a todoItem

            // remove errorLabel if it exists
            if(rootPane.getChildren().get(2).getId() != null) {
                if(rootPane.getChildren().get(2).getId().equals("errorLabel")) {
                    rootPane.getChildren().remove(2);
                }
            }

            HBox todoCard = FXMLLoader.load(getClass().getResource("../views/todocard.fxml"));
            Label oldLabel = (Label) todoCard.getChildren().get(1);

            oldLabel.setText(todoTitle);
            todoCard.getChildren().set(1, oldLabel);
            todoList.getChildren().add(todoCard);

            // clear todofield
            addTodoField.setText("");
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            addTodo();
        }
    }
}
