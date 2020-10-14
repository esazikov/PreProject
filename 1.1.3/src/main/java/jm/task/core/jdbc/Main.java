package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        //UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Smith", (byte) 64);
        userService.saveUser("Jonney", "Smither", (byte) 53);
        userService.saveUser("Djohnatan", "Smithest", (byte) 42);
        userService.saveUser("Johan", "Smithy", (byte) 31);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();

    }
}
