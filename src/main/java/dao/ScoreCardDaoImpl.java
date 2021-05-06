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
    @Override
    public void calcBonus(Integer card_id){
        ScoreCard card = findScoreCard(card_id);
        Integer sum = 0;
        sum += card.getOnes();
        sum += card.getTwos();
        sum += card.getThrees();
        sum += card.getFours();
        sum += card.getFives();
        sum += card.getSixes();
        if (sum >= 61){
            card.setBonus(50);
            em.merge(card);
        }
    }
    @Override
    public void calcTotal(Integer card_id){
        ScoreCard card = findScoreCard(card_id);
        Integer sum = 0;
        sum += card.getOnes();
        sum += card.getTwos();
        sum += card.getThrees();
        sum += card.getFours();
        sum += card.getFives();
        sum += card.getSixes();
        sum += card.getBonus();
        sum += card.getThree_kind();
        sum += card.getFour_kind();
        sum += card.getFull_house();
        sum += card.getSmall_straight();
        sum += card.getLarge_straight();
        sum += card.getYatzy();
        sum += card.getChance();
        card.setTotal(sum);
        em.merge(card);
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
