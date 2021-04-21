package dao;

import model.ScoreCard;

import java.util.List;

public interface ScoreCardDao {

    List<ScoreCard> findAll();
    void createScoreCard( ScoreCard  scoreCard);
}
