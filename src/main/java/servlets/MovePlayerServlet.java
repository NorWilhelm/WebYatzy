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
public class MovePlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ScoreCardDao scoreCardDao;
    @EJB
    private GameDao gameDao;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {


        String username = (String) request.getAttribute("username");
        String game_id =  (String) request.getAttribute("game_id");

        ScoreCard player_score_card = new ScoreCard();
        scoreCardDao.createScoreCard(player_score_card);
        Integer player_scid = player_score_card.getScore_card_id();

        Game new_game = new Game(username, player_scid);
        System.out.println(new_game.getActive_player());
        gameDao.createGame(new_game);



        request.setAttribute("game_id", new_game.getGame_id());
        request.setAttribute("username", username);
        request.getRequestDispatcher("lobbies.jsp").forward(request, response);
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