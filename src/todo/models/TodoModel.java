package todo.models;

import todo.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
