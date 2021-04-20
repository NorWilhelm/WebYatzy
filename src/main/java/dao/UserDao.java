package dao;

import model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    void createUser(User user);
}
