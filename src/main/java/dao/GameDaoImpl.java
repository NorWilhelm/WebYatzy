package dao;

import model.Game;
import model.User;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    public void removeGame(Integer id){
        Game foundGame = em.find(Game.class, id);
        assert foundGame != null : "Game with id " + id + " hasn't been found in the database!";
        em.remove(foundGame);
    }

    //We assume that when state = 0 the game has been created but haven't been started yet
    @Override
    public List<Game> findPreGames() {
        return em.createQuery("SELECT e FROM Game e where e.gamestate = :state",
        Game.class).setParameter("state", "0").getResultList();
    }

    @Override
    public void updateGameState(Integer id, String state) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();    //The entity gets persistant and the changes are synchronized automatically before we commit
        Game game = em.find(Game.class, id);
        game.setGamestate(state);
        transaction.commit();
    }

    @Override
    public Game findGame(Integer id) {
        return em.find(Game.class, id);
    }


}
