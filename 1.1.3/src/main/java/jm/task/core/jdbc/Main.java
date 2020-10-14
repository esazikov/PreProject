package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("John", "Smith", (byte) 64);
        userDaoJDBC.saveUser("Jonney", "Smither", (byte) 53);
        userDaoJDBC.saveUser("Djohnatan", "Smithest", (byte) 42);
        userDaoJDBC.saveUser("Johan", "Smithy", (byte) 31);
        System.out.println(userDaoJDBC.getAllUsers());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
        userDaoJDBC.disconnect();
    }
}
