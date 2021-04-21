package dao;

import model.Game;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class GameDaoImpl implements GameDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Game> findAll() {
        return em.createNamedQuery("Game.findAll", Game.class).getResultList();
    }

    @Override
    public void createGame(Game game) {
        em.persist(game);

    }
}
