package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util implements AutoCloseable{

    private static Connection connection = null;

    public Util() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "admin", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false", "admin", "12345");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
