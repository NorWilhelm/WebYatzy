package dao;

import model.ScoreCard;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
