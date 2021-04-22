package dao;

import model.ScoreCard;
import model.User;

import java.util.List;

public interface ScoreCardDao {

    List<ScoreCard> findAll();
    void createScoreCard (ScoreCard  scoreCard);
    ScoreCard findScoreCard (Integer id);
    void removeScoreCard (Integer id);
    void updateScoreCard (Integer count, User user);
}
