package todo.models;

import todo.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodoModel {

    private static TodoModel INSTANCE = null;

    Connection connection;

    public TodoModel() {
        connection = SqliteConnection.connector();
        if(connection == null) {
            System.out.println("FATAL ERROR: SQLITE CONNECTION PROBLEM");
            System.out.println("EXITING!");
            System.exit(0);
        }
    }

    public static TodoModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TodoModel();
        }
        return INSTANCE;
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean isTodoPresent() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select title from todos";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> getTodos() throws SQLException {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String query = "select title from todos";

        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        ArrayList<String> todos = new ArrayList<>();
        while(resultSet.next()) {
            todos.add(resultSet.getString("title"));
        }

        return todos;
    }

    public void addTodo(String title) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "INSERT INTO todos (title) VALUES( ? );";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.executeUpdate();
    }

    public void deleteTodo(String title) throws SQLException {
        PreparedStatement preparedStatement;
        String query = "WITH todo AS (SELECT id FROM todos WHERE title = ? LIMIT 1) DELETE FROM todos WHERE id = (SELECT id FROM todo)";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.executeUpdate();
    }

}
