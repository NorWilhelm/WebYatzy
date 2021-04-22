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

@WebServlet("/move_player")
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
        Game game = gameDao.findGame(Integer.parseInt(game_id));

        if(game.getUsername_host().equals(username))
            game.setHost_scid(player_scid);
        else if(game.getUsername_p2().equals(username))
            game.setPlayer_2_scid(player_scid);
        else if(game.getUsername_p3().equals(username))
            game.setPlayer_3_scid(player_scid);
        else if(game.getUsername_p4().equals(username))
            game.setPlayer_4_scid(player_scid);
        else if(game.getUsername_p5().equals(username))
            game.setPlayer_5_scid(player_scid);


        request.setAttribute("game_id", game.getGame_id());
        request.setAttribute("username", username);
        request.getRequestDispatcher("games.jsp").forward(request, response);
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