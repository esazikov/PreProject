package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactory();
    private Session session;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users ("+
                "id BIGINT NOT NULL AUTO_INCREMENT, "+
                "name VARCHAR(15) NOT NULL, "+
                "lastname VARCHAR(30) NOT NULL, "+
                "age TINYINT NOT NULL, "+
                "PRIMARY KEY (id));").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM users WHERE id=:userId;")
                .setParameter("userId", id)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE FROM users;").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
