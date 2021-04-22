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

@WebServlet("/join_lobby")
public class StartGameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private GameDao gameDao;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        //Checks if players are in one and only game
        //Check the game state to ongoing
        Integer gameId = Integer.parseInt(request.getParameter("game_id"));
        String userName = (String) request.getParameter("username");
        if (gameDao.areInOneGame(gameId)) {
            gameDao.updateGameState(gameId, "ongoing");
        } else {
            List<String> assholes = gameDao.findPlayerThatHasJoinedMultipleGames(gameId);
            if (!assholes.isEmpty()) {
                for (int i = 0; i < assholes.size(); i++) {
                    gameDao.removePlayer(gameId, assholes.get(i));
                }
            }
            gameDao.updateGameState(gameId, "ongoing");
        }
        request.setAttribute("game_id", gameId);
        request.setAttribute("username", userName);
        request.getRequestDispatcher("Game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // TODO: Do something here... Post new content? -Or perhaps quit to startpage

        String content = request.getParameter("newContent"); // The new content which shall be sent to the DB

    }
}
