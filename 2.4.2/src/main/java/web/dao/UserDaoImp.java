package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void add(User user) {
      entityManager.persist(user);
   }

   @Override
   public void deleteUserById(Long id) {
      entityManager.remove(getUserById(id));
   }

   @Override
   public void edit(User user) {
      entityManager.merge(user);
   }

   @Override
   public List<User> getAllUsers() {
      return entityManager.createQuery("from User",User.class).getResultList();
   }

   @Override
   public User getUserById(Long id) {
      return entityManager.find(User.class, id);
   }

   @Override
   public User getUserByName(String username) {
      return entityManager.createQuery(
              "select u from User u where u.userName = :name", User.class)
              .setParameter("name", username)
              .getSingleResult();
   }
}
