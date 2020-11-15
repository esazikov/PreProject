package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> getAllUsers();
    void deleteUserbyId(Long id);
    void edit(User user);

    User getUserbyId(Long id);
}
