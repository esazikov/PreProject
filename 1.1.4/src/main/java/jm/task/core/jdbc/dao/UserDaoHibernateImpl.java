package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    //private SessionFactory sessionFactory = Util.getSessionFactory();
    private Session session;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users ("+
                    "id BIGINT NOT NULL AUTO_INCREMENT, "+
                    "name VARCHAR(15) NOT NULL, "+
                    "lastname VARCHAR(30) NOT NULL, "+
                    "age TINYINT NOT NULL, "+
                    "PRIMARY KEY (id));").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery("DELETE User WHERE id =:userId")
                    .setParameter("userId", id)
                    .executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        session = Util.getSessionFactory().openSession();
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("DELETE FROM users;").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
