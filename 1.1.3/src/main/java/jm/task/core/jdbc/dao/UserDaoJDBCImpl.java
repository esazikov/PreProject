package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    // private Util util = new Util();
    //private Connection connection;
    private Statement statement;

    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (Util util = new Util()){
            statement = util.getConnection().createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users ("+
                    "id BIGINT NOT NULL AUTO_INCREMENT, "+
                    "name VARCHAR(15) NOT NULL, "+
                    "lastname VARCHAR(30) NOT NULL, "+
                    "age TINYINT NOT NULL, "+
                    "PRIMARY KEY (id));";
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Util util = new Util()){
            statement = util.getConnection().createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Util util = new Util()){
            statement = util.getConnection().createStatement();
            String sql = "INSERT INTO users (name, lastname, age) VALUES ('" + name + "', '" + lastName + "', " + age +");";
            statement.executeUpdate(sql);
            statement.close();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Util util = new Util()){
            statement = util.getConnection().createStatement();
            String sql = "DELETE FROM users WHERE id = " + id + ";";
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Util util = new Util()){
            statement = util.getConnection().createStatement();
            String sql = "SELECT * FROM users;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                userList.add(new User(resultSet.getString("name"), resultSet.getString("lastname"), resultSet.getByte("age")));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }return userList;
    }

    public void cleanUsersTable() {
        try (Util util = new Util()){
            statement = util.getConnection().createStatement();
            String sql = "DELETE FROM users;";
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
