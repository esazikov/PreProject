package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util implements AutoCloseable{

    private static Connection connection = null;

    public Util() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC&useSSL=false", "admin", "12345");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC&useSSL=false", "admin", "12345");
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

    public static SessionFactory getSessionFactory() {
        Configuration config = new Configuration()
                .setProperty("hibernate.driver", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.username", "admin")
                .setProperty("hibernate.connection.password", "12345")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibenate.current_session_context_class", "thread")
                .addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();
        return config.buildSessionFactory(serviceRegistry);
    }
}
