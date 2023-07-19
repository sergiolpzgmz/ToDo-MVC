package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String url = "jdbc:mysql://localhost/todoapp";
    private static final String userName = "root";
    private static  final String password = "root";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null)connection = DriverManager.getConnection(url,userName,password);
        return connection;
    }
}