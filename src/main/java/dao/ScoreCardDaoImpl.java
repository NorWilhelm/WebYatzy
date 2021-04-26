package dao;

import model.Game;
import model.ScoreCard;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.util.List;

@Stateless
public class ScoreCardDaoImpl implements ScoreCardDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ScoreCard> findAll() {
        return em.createNamedQuery("ScoreCard.findAll", ScoreCard.class).getResultList();
    }

    @Override
    public void createScoreCard(ScoreCard scoreCard) {
        em.persist(scoreCard);
    }

    @Override
    public void removeScoreCard (Integer id){
        ScoreCard foundScoreCard = em.find(ScoreCard.class, id);
        assert foundScoreCard != null : "The card with id " + id + " hasn't been found in the database!";
    }

    @Override
    public void updateScore(Integer scorecard_id, Integer round, Integer score){
        ScoreCard score_card = findScoreCard(scorecard_id);
        score_card.setScoreFromRound(round, score);
        em.merge(score_card);
    }

//    @Override
//    public void updateScoreCard (Integer count, User user) {
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        ScoreCard card = em.find(ScoreCard.class, user.getScoreCardId());
//        transaction.commit();
//    }

    @Override
    public ScoreCard findScoreCard (Integer id){
       return em.find(ScoreCard.class, id);
    }
}
