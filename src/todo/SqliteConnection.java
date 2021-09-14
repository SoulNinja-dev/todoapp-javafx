package todo;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteConnection {
    public static Connection connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:mydb.db");
            return conn;
        } catch (Exception e){
            return null;
        }
    }
}
