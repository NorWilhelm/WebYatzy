package dao;

import model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    void createUser(User user);
    public User findUser (String username);
//    public void updateUserScoreCard (int count ,String userName);
}
