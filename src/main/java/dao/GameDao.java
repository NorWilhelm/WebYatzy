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
    List<Game> findOnGoing();
    void updateGameState (Integer id, String state);
    void updateGameDice1(Integer id, Integer dice5);
    void updateGameDice2(Integer id, Integer dice5);
    void updateGameDice3(Integer id, Integer dice5);
    void updateGameDice4(Integer id, Integer dice5);
    void updateGameDice5(Integer id, Integer dice5);
    boolean isTheTrueHost(String username);
    Integer findPlayerLobby(String username);
    List<String> fetchLobbyPlayers(Integer gameID);
    void removePlayer(Integer gameID, String username);
    void joinPlayer(Integer gameID, String username);
}
