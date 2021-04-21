package dao;

import model.ScoreCard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    public ScoreCard getScoreCard (Integer id){
       ScoreCard tagretScoreCard = null;
       List<ScoreCard> scoreCards = findAll();
       for (ScoreCard sc : scoreCards){
           if ((sc.getScore_card_id().intValue()) == id){
               tagretScoreCard = sc;
           }
       }
       assert tagretScoreCard != null : "The score card you have tried to reach hasn't been found! ScoreCardDAO.getScoreCard()";
       return tagretScoreCard;
    }
}
