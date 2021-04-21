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
        Game.class).setParameter("state", "pre_game").getResultList();
    }
    //We assume that when state = 0 the game has been created but haven't been started yet
    @Override
    public List<Game> findOnGoing() {
        return em.createQuery("SELECT e FROM Game e where e.gamestate = :state",
                Game.class).setParameter("state", "ongoing_game").getResultList();
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

    @Override
    public Integer findPlayerLobby(String username){
        List<Game> pre_games = findPreGames();
        for(var pre_game : pre_games){
            for(String name : fetchLobbyPlayers(pre_game.getGame_id())){
                if (name.equals(username)){
                    return pre_game.getGame_id();
                }
            }
        }
        List<Game> ongoing_games = findOnGoing();
        for(var ongoing_game : ongoing_games){
            for(String name : fetchLobbyPlayers(ongoing_game.getGame_id())){
                if (name.equals(username)){
                    return ongoing_game.getGame_id();
                }
            }
        }
    return null;
    }

    @Override
    public List<String> fetchLobbyPlayers(Integer gameID){
        return findGame(gameID).getPlayers();

    }



    @Override
    public void removePlayer(Integer gameID, String username) {
        Game game = em.find(Game.class, gameID);

        if (game.getUsername_p2().equals(username))
            game.setUsername_p2(null);
        else if (game.getUsername_p3().equals(username))
            game.setUsername_p3(null);
        else if (game.getUsername_p4().equals(username))
            game.setUsername_p4(null);
        else if (game.getUsername_p5().equals(username))
            game.setUsername_p5(null);

        em.merge(game);
    }

    @Override
    public void joinPlayer(Integer gameID, String username) {
        Game game = em.find(Game.class, gameID);

        if (game.getUsername_p2().equals(null))
            game.setUsername_p2(username);
        else if (game.getUsername_p3().equals(null))
            game.setUsername_p3(username);
        else if (game.getUsername_p4().equals(null))
            game.setUsername_p4(username);
        else if (game.getUsername_p5().equals(null))
            game.setUsername_p5(username);

        em.merge(game);
    }

}
