package dao;

import model.Game;
import model.User;

import java.util.List;

public interface GameDao {

    List<Game> findAll();
    void createGame (Game game);
    Game findGame (Integer id);
    void removeGame (Integer id);
    List<Game> findPreGames ();
    void updateGameState (Integer id, String state);
    boolean isTheTrueHost (String username);
    public void updateGameDice1 (Integer id, Integer dice1);
    public void updateGameDice2 (Integer id, Integer dice2);
    public void updateGameDice3 (Integer id, Integer dice3);
    public void updateGameDice4 (Integer id, Integer dice4);
    public void updateGameDice5 (Integer id, Integer dice5);
}
