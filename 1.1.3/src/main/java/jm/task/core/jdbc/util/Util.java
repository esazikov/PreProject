package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util implements AutoCloseable{

    private Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC&useSSL=false", "admin", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
