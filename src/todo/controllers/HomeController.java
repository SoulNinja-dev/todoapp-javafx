package todo.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import todo.models.TodoModel;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public TodoModel todoModel = new TodoModel();

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

    @FXML
    public void onKeyPressed(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER) {
            addTodo();
        }
    }

    private void addTodo() throws IOException {
        removeErrors();
        // getting text inside textfield
        String todoTitle = addTodoField.getText();

        // when todoTitle is greater than 50
        if(todoTitle.length() > 50) {
            maxLengthError();
        }
        // when todoTitle is null
        else if(todoTitle.isEmpty()) {
            nullLengthError();
        }
        // perfect scenario
        else {
            addTodoToPane(todoTitle);
        }
    }

    private void maxLengthError() throws IOException {
        Label maxLengthError = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../components/maxLength.fxml")));
        rootPane.getChildren().add(2, maxLengthError);
    }

    private void nullLengthError() throws IOException {
        removeErrors();
        Label error = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../components/errorLabel.fxml")));
        rootPane.getChildren().add(2, error);
    }

    private void addTodoToPane(String todoTitle) throws IOException {
        removeErrors();
        HBox todoCard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../views/todocard.fxml")));
        Label oldLabel = (Label) todoCard.getChildren().get(1);

        oldLabel.setText(todoTitle);
        todoCard.getChildren().set(1, oldLabel);
        todoList.getChildren().add(todoCard);

        // clear todofield
        addTodoField.setText("");
    }

    private void removeErrors() {
        // checks if error exists, then removes it
        ObservableList<Node> children = rootPane.getChildren();
        for(int i = 0;i<children.size();i++) {
            if(children.get(i).getId() == null){}
            else if(children.get(i).getId().equals("errorLabel")) {
                rootPane.getChildren().remove(i);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(todoModel.isDbConnected()) {
            System.out.println("Db is connected");
        } else {
            System.out.println("Nope, not connected");
        }
    }
}
