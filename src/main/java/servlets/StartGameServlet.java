package servlets;

import dao.GameDao;
import dao.ScoreCardDao;
import dao.UserDao;
import model.Game;
import model.ScoreCard;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/startGame")
public class StartGameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private GameDao gameDao;
    @EJB
    private ScoreCardDao scoreCardDao;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        Integer game_id =  Integer.parseInt(request.getParameter("game_id"));
        System.out.println("STARTGAME");
        System.out.println("GAME ID :" +game_id);
        System.out.println("GAME ID :" +game_id);

        String username = request.getParameter("username");
        System.out.println("Username :" + username);
        System.out.println("Username :" + username);
        ScoreCard host_score_card = new ScoreCard();
        scoreCardDao.createScoreCard(host_score_card);
        ScoreCard p2_score_card = new ScoreCard();
        scoreCardDao.createScoreCard(p2_score_card);
        ScoreCard p3_score_card = new ScoreCard();
        scoreCardDao.createScoreCard(p3_score_card);
        ScoreCard p4_score_card = new ScoreCard();
        scoreCardDao.createScoreCard(p4_score_card);
        ScoreCard p5_score_card = new ScoreCard();
        scoreCardDao.createScoreCard(p5_score_card);

        Integer host_scid = host_score_card.getScore_card_id();
        Integer p2_scid = p2_score_card.getScore_card_id();
        Integer p3_scid = p3_score_card.getScore_card_id();
        Integer p4_scid = p4_score_card.getScore_card_id();
        Integer p5_scid = p5_score_card.getScore_card_id();


        gameDao.startGame(game_id, host_scid, p2_scid, p3_scid, p4_scid, p5_scid);

        request.setAttribute("username", username);
        request.setAttribute("game_id", game_id);
        System.out.println("Start game finished1");
        request.getRequestDispatcher("/game_session.jsp").forward(request, response);
        System.out.println("Start game finished2");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // TODO: Do something here... Post new content? -Or perhaps quit to startpage

        String content = request.getParameter("newContent"); // The new content which shall be sent to the DB

    }
}
