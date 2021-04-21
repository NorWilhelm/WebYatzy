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

@WebServlet("/create_lobby")
public class JoinLobbyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private GameDao gameDao;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        String game_id =  (String) request.getAttribute("game_id");
        String username =  (String) request.getAttribute("username");

        gameDao.removePlayer(gameDao.findPlayerLobby(username), username);
        Game game = gameDao.findGame(Integer.parseInt(game_id));

        gameDao.joinPlayer(game.getGame_id(), username);

        request.setAttribute("game_id", game.getGame_id());
        request.setAttribute("username", username);
        request.getRequestDispatcher("lobbies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // TODO: Do something here... Post new content? -Or perhaps quit to startpage

        String content = request.getParameter("newContent"); // The new content which shall be sent to the DB

    }
}