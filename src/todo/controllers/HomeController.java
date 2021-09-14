package todo.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<String> existingTodo;

        if(todoModel.isDbConnected()) {
            System.out.println("Db is connected");
        } else {
            System.out.println("Nope, not connected");
        }

        try {
            if(todoModel.isTodoPresent()) {
                existingTodo = todoModel.getTodos();
                addOldTodos(existingTodo);
            } else {
                addNothingLabel();
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void onAvatarClick(ActionEvent event) {
        System.out.println("avatar was clicked");
    }

    @FXML
    public void onMenuClick(ActionEvent event) {
        System.out.println("menu was clicked");
    }

    @FXML
    public void onAddTodoClick(ActionEvent event) throws IOException, SQLException {
        // getting text inside textfield
        String todoTitle = addTodoField.getText();
        addTodo(todoTitle);
        todoModel.addTodo(todoTitle);
    }

    @FXML
    public void onKeyPressed(KeyEvent event) throws IOException, SQLException {
        if(event.getCode() == KeyCode.ENTER) {
            // getting text inside textfield
            String todoTitle = addTodoField.getText();
            addTodo(todoTitle);
            todoModel.addTodo(todoTitle);
        }
    }

    private void addTodo(String todoTitle) throws IOException {
        removeErrors();

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
        ObservableList<Node> errors = rootPane.getChildren();
        ObservableList<Node> todos = todoList.getChildren();

        for(int i = 0;i<errors.size();i++) {
            if(errors.get(i).getId() == null){}
            else if(errors.get(i).getId().equals("errorLabel")) {
                rootPane.getChildren().remove(i);
            }
        }

        for(int i = 0;i<todos.size();i++) {
            if(todos.get(i).getId() == null){}
            else if(todos.get(i).getId().equals("errorLabel")) {
                todoList.getChildren().remove(i);
            }
        }
    }

    private void addNothingLabel() throws IOException {
        Parent label = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../components/nothingTodo.fxml")));
        todoList.getChildren().add(label);
    }

    private void addOldTodos(ArrayList<String> existingTodo) throws IOException {
        for(String todo:existingTodo) {
            addTodo(todo);
        }
    }
}
