package service;

import java.util.List;

import model.User;

public interface UserService {
    User getUserById(int id);
    List<User> getAllUsers();
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
}