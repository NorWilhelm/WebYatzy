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
        request.getRequestDispatcher("lobbies.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        System.out.println("MOVE PLAYER");
        String username = (String) request.getParameter("username");
        String game_id =  (String) request.getParameter("game_id");

        System.out.println("STARTGAME");
        System.out.println("GAME ID :" +game_id);
        System.out.println("GAME ID :" +game_id);
        System.out.println("GAME ID :" +game_id);
        System.out.println("GAME ID :" +game_id);
        System.out.println("GAME ID :" +game_id);
        System.out.println("GAME ID :" +game_id);

        System.out.println("Username :" + username);
        System.out.println("Username :" + username);
        System.out.println("Username :" + username);
        System.out.println("Username :" + username);
        System.out.println("Username :" + username);
        // TODO Check if player has score card for this game, and if so skip making a new

        // TODO remove player from all other pre_games and ongoing_games other than this game




        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(username);

        request.setAttribute("game_id", game_id);
        request.setAttribute("username", username);

        // String gameID = request.setAttribute("gameID", "2"); // TODO: Get the data from DB - For now, gameID is just a placeholder object
        // String content = request.setAttribute("content", content); //
        System.out.println("Move finished ");
    }
}