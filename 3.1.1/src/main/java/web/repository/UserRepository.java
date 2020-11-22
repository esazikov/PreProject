package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import web.model.User;

@Service
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String name);
}
