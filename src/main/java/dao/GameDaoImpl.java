package dao;

import model.Game;
import model.User;
import org.hibernate.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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
    //We assume that when state = 0 the game has been created but hasn't been started yet
    @Override
    public List<Game> findOnGoing() {
        return em.createQuery("SELECT e FROM Game e where e.gamestate = :state",
                Game.class).setParameter("state", "ongoing_game").getResultList();
    }

    @Override
    public void updateGameState(Integer id, String state) {
        Game game = em.find(Game.class, id);
        game.setGamestate(state);
        em.merge(game);
    }

    @Override
    public boolean isInOneGame (String userName){
        List<Game> games = findAll();
        int count = (int) games.stream().filter(e -> e.getUsername_host() == (userName) || e.getUsername_p2() == (userName) || e.getUsername_p3() == (userName) || e.getUsername_p4() == (userName) || e.getUsername_p5() == (userName)).count();
        return (count == 1);
    }

    @Override
    public boolean areInOneGame(Integer id) {
        boolean areInOneGame = true;
        Game game = findGame(id);
        String [] players = {game.getUsername_host(), game.getUsername_p2(), game.getUsername_p3(), game.getUsername_p3(), game.getUsername_p4(), game.getUsername_p5()};
        for (String e : players){
            areInOneGame = isInOneGame(e);
        }
        return areInOneGame;
    }

    @Override
    public List<String> findPlayerThatHasJoinedMultipleGames(Integer id) {
        List<String> playersInMultipleGames = new ArrayList<>();
        Game currentGame = findGame(id);
        String [] players = {currentGame.getUsername_host(), currentGame.getUsername_p2(), currentGame.getUsername_p3(), currentGame.getUsername_p3(), currentGame.getUsername_p4(), currentGame.getUsername_p5()};
        for (String e : players){
            if (!isInOneGame(e)){
                playersInMultipleGames.add(e);
            }
        }
        return playersInMultipleGames;
    }

    @Override
    public void updateGameDice1 (Integer id, Integer dice1){
        Game game = em.find(Game.class, id);
        game.setDice1(dice1);
        em.merge(game);
    }

    @Override
    public void updateGameDice2 (Integer id, Integer dice2){
        Game game = em.find(Game.class, id);
        game.setDice2(dice2);
        em.merge(game);
    }

    @Override
    public void updateGameDice3 (Integer id, Integer dice3){
        Game game = em.find(Game.class, id);
        game.setDice3(dice3);
        em.merge(game);
    }

    @Override
    public void updateGameDice4 (Integer id, Integer dice4){
        Game game = em.find(Game.class, id);
        game.setDice4(dice4);
        em.merge(game);
    }
    @Override
    public void updateGameDice5 (Integer id, Integer dice5){
        Game game = em.find(Game.class, id);
        game.setDice5(dice5);
        em.merge(game);
    }

    @Override
    public void updateDice (Integer gameID, Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5){
        updateGameDice1(gameID, dice1);
        updateGameDice2(gameID, dice2);
        updateGameDice3(gameID, dice3);
        updateGameDice4(gameID, dice4);
        updateGameDice5(gameID, dice5);


    }

//    @Override
//    public void advanceTurn (String username) {
//
//    }





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
    public boolean isHostingPre(String username) {
        for (Game game : findPreGames()){
            if (game.getUsername_host().equals(username))
               return true;
    }
        return false;
    }

    @Override
    public boolean isJoinedPre(String username) {
        for (Game game : findPreGames()){
            if (game.getJoinedPlayers().contains(username))
                return true;
        }
        return false;
    }

    @Override
    public Game findGame(Integer id) {
        return em.find(Game.class, id);
    }

    @Override
    public Integer findPlayerLobby(String username){
        List<Game> pre_games = findPreGames();
        for(var pre_game : pre_games){
            for(String name : pre_game.getPlayers()){
                if (name.equals(username)){
                    return pre_game.getGame_id();
                }
            }
        }
        List<Game> ongoing_games = findOnGoing();
        for(Game ongoing_game : ongoing_games){
            for(String name : ongoing_game.getPlayers()){
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

        if (game.getUsername_p2() != null && game.getUsername_p2().equals(username))
            game.setUsername_p2(null);
        else if (game.getUsername_p3() != null && game.getUsername_p3().equals(username))
            game.setUsername_p3(null);
        else if (game.getUsername_p4() != null && game.getUsername_p4().equals(username))
            game.setUsername_p4(null);
        else if (game.getUsername_p5() != null && game.getUsername_p5().equals(username))
            game.setUsername_p5(null);

        em.merge(game);
    }

    @Override
    public void joinPlayer(Integer gameID, String username) {
        Game game = em.find(Game.class, gameID);

        if (game.getUsername_p2() == null)
            game.setUsername_p2(username);
        else if (game.getUsername_p3() == null)
            game.setUsername_p3(username);
        else if (game.getUsername_p4() == null)
            game.setUsername_p4(username);
        else if (game.getUsername_p5() == null)
            game.setUsername_p5(username);

        em.merge(game);
    }


}
