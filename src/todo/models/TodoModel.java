package todo.models;

import todo.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TodoModel {

    Connection connection;

    public TodoModel() {
        connection = SqliteConnection.connector();
        if(connection == null) {
            System.out.println("FATAL ERROR: SQLITE CONNECTION PROBLEM");
            System.out.println("EXITING!");
            System.exit(0);
        }
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

            if(resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            Objects.requireNonNull(preparedStatement).close();
            Objects.requireNonNull(resultSet).close();
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
}
