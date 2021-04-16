package dao;

import model.User;

import java.util.List;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {

    @PersistenceContext(name = "userPU")
    private EntityManager em;

    public List<User> fetchAllUsers() {
        return em.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    public User fetchUser(String username) {
        return em.find(User.class, username);
    }

    public void createUser(User newUser) {
        em.persist(newUser);
    }
}
