package dao;

import model.Game;
import model.ScoreCard;
import model.User;
import org.hibernate.Session;
import utility.Util;

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
    @Override
    public void updateDiceState1(Integer gameID, boolean is_kept ) {
        Game game = findGame(gameID);
        if (is_kept){
            game.setDice1(Math.abs(game.getDice1()) * -1);
        } else{
            game.setDice1(Math.abs(game.getDice1()));}
        em.merge(game);
    }
    @Override
    public void updateDiceState2(Integer gameID, boolean is_kept ) {
        Game game = findGame(gameID);
        if (is_kept){
            game.setDice2(Math.abs(game.getDice2()) * -1);
        } else{
            game.setDice2(Math.abs(game.getDice2()));}
        em.merge(game);
    }

    @Override
    public void updateDiceState3(Integer gameID, boolean is_kept ) {
        Game game = findGame(gameID);
        if (is_kept){
            game.setDice3(Math.abs(game.getDice3()) * -1);
        } else{
            game.setDice3(Math.abs(game.getDice3()));}
        em.merge(game);
    }
    @Override
    public void updateDiceState4(Integer gameID, boolean is_kept ) {
        Game game = findGame(gameID);
        if (is_kept){
            game.setDice4(Math.abs(game.getDice4()) * -1);
        } else{
            game.setDice4(Math.abs(game.getDice4()));}
        em.merge(game);
    }
    @Override
    public void updateDiceState5(Integer gameID, boolean is_kept ) {
        Game game = findGame(gameID);
        if (is_kept){
            game.setDice5(Math.abs(game.getDice5()) * -1);
        } else{
            game.setDice5(Math.abs(game.getDice5()));}
        em.merge(game);
    }


    @Override
    public void updateDiceThrow(Integer gameID ) {
        Game game = findGame(gameID);

        if (game.getDice1() > 0){
            game.setDice1(Util.diceRoll());
        }
        if (game.getDice2() > 0){
            game.setDice2(Util.diceRoll());
        }
        if (game.getDice3() > 0){
            game.setDice3(Util.diceRoll());
        }
        if (game.getDice4() > 0){
            game.setDice4(Util.diceRoll());
        }
        if (game.getDice5() > 0){
            game.setDice5(Util.diceRoll());
        }

        game.setCurrent_throw(game.getCurrent_throw() + 1);
        em.merge(game);
    }

    @Override
    public void progressTurn(Integer gameID) {
        Game game = findGame(gameID);
        game.setCurrent_throw(1);
        game.setDice1(Util.diceRoll());
        game.setDice2(Util.diceRoll());
        game.setDice3(Util.diceRoll());
        game.setDice4(Util.diceRoll());
        game.setDice5(Util.diceRoll());
        String active_player = game.getActive_player();
        List<String> players = game.getPlayers();
        String last_player = players.get(players.size()-1);

        if(active_player.equals(last_player)){
            game.setCurrent_round(game.getCurrent_round() + 1);
            game.setActive_player(players.get(0));
        } else {
            String next_player = players.get(players.lastIndexOf(active_player) + 1);
            game.setActive_player(next_player);
        }

        if(game.getCurrent_round() == 16){
            game.setGamestate("post_game");
        }
        em.merge(game);

    }

    @Override
    public void startGame(Integer gameID, Integer host_scid, Integer p2_scid, Integer p3_scid, Integer p4_scid, Integer p5_scid) {
        Game game = findGame(gameID);
        game.setGamestate("ongoing_game");
        game.setHost_scid(host_scid);
        game.setPlayer_2_scid(p2_scid);
        game.setPlayer_3_scid(p3_scid);
        game.setPlayer_4_scid(p4_scid);
        game.setPlayer_5_scid(p5_scid);
        game.setCurrent_throw(1);
        game.setCurrent_round(1);
        game.setDice1(Util.diceRoll());
        game.setDice2(Util.diceRoll());
        game.setDice3(Util.diceRoll());
        game.setDice4(Util.diceRoll());
        game.setDice5(Util.diceRoll());
        em.merge(game);



    }

}
