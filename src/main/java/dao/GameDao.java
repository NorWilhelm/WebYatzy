package dao;

import model.Game;
import model.User;

import java.util.List;

public interface GameDao {

    List<Game> findAll();
    void createGame(Game game);
}
