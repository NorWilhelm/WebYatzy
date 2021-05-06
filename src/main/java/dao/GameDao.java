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
    void updateDice (Integer gameID, Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5);
    boolean isHostingPre(String username);
    boolean isJoinedPre(String username);
    boolean isInOneGame (String userName);
    boolean areInOneGame (Integer id);
    List<String> findPlayerThatHasJoinedMultipleGames(Integer id);
    void progressTurn(Integer gameID);
    void updateDiceThrow(Integer gameID);
    void startGame(Integer gameID, Integer host_scid, Integer p2_scid, Integer p3_scid, Integer p4_scid, Integer p5_scid);
    void updateDiceState1(Integer gameID, boolean is_kept );
    void updateDiceState2(Integer gameID, boolean is_kept );
    void updateDiceState3(Integer gameID, boolean is_kept );
    void updateDiceState4(Integer gameID, boolean is_kept );
    void updateDiceState5(Integer gameID, boolean is_kept );
    }
