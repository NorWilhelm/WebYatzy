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
    public void updateGameDice1 (Integer id, Integer dice1){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Game game = em.find(Game.class, id);
        game.setDice1(dice1);
        transaction.commit();
    }

    @Override
    public void updateGameDice2 (Integer id, Integer dice2){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Game game = em.find(Game.class, id);
        game.setDice2(dice2);
        transaction.commit();
    }

    @Override
    public void updateGameDice3 (Integer id, Integer dice3){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Game game = em.find(Game.class, id);
        game.setDice3(dice3);
        transaction.commit();
    }

    @Override
    public void updateGameDice4 (Integer id, Integer dice4){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Game game = em.find(Game.class, id);
        game.setDice4(dice4);
        transaction.commit();
    }
    @Override
    public void updateGameDice5 (Integer id, Integer dice5){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Game game = em.find(Game.class, id);
        game.setDice5(dice5);
        transaction.commit();
    }

    @Override
    public boolean isTheTrueHost(String username) {
        List<Game> allGames = findAll();
        int i = 0;
        while (i < 2){
            for (Game g : allGames){
                if (g.getUsername_host().equals(username)){
                    i++;
                }
            }
        }
        switch (i) {
            case (0):
                System.out.println(username + " is not hosting any games!");
                return false;
            case (1):
                return true;
            case (2):
                return false;
            default:
                return false;
        }
    }

    @Override
    public Game findGame(Integer id) {
        return em.find(Game.class, id);
    }


}
