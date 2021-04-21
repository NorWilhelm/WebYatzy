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

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/new_pre_game")
public class PreGameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ScoreCardDao scoreCardDao;
    @EJB
    private GameDao gameDao;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        System.out.println("New Game");
        String username = (String) request.getParameter("username");
        ScoreCard host_score_card = new ScoreCard();
        scoreCardDao.createScoreCard(host_score_card);
        Integer host_scid = host_score_card.getScore_card_id();

        Game new_game = new Game(username, host_scid);
        System.out.println(new_game.getActive_player());
        gameDao.createGame(new_game);


        String game_id = (String) request.getAttribute("game_id");

        String text = "some teasdasdasdasdasdasdasdxt";
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(text);
        // String gameID = request.setAttribute("gameID", "2"); // TODO: Get the data from DB - For now, gameID is just a placeholder object
        // String content = request.setAttribute("content", content); //

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // TODO: Do something here... Post new content? -Or perhaps quit to startpage

        String content = request.getParameter("newContent"); // The new content which shall be sent to the DB

    }
}