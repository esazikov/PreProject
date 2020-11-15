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
   public void deleteUserbyId(Long id) {
      entityManager.remove(getUserbyId(id));
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
   public User getUserbyId(Long id) {
      return entityManager.find(User.class, id);
   }
}
