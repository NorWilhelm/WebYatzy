package dao;

import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        em.persist(user);
    }

    @Override
    public User findUser (String username){
       return em.find(User.class, username);
    }

    @Override
    public void updateUserScoreCard (int count ,String userName){
        User user = findUser(userName);
        ScoreCardDao scDao = new ScoreCardDaoImpl();
        scDao.updateScoreCard(count, user);
    }
}
