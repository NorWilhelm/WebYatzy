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
    void removePlayer(Integer gameID, String username);
    List<String> fetchLobbyPlayers(Integer gameID);
    List<Game> findOnGoing();
    Integer findPlayerLobby(String username);
    void joinPlayer(Integer gameID, String username);
}
