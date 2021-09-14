package todo.models;

import todo.SqliteConnection;

import java.sql.Connection;
import java.sql.SQLException;

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
}
