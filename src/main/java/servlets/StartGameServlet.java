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
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        String gameId = (String) request.getParameter("game_id");    //returns null TODO: answer the question: Why does it return null?
        int gameIdInt = Integer.parseInt(gameId);
        String userName = (String) request.getParameter("username");
        if (gameDao.areInOneGame(gameIdInt)) {
            gameDao.updateGameState(gameIdInt, "ongoing");
        } else {
            List<String> assholes = gameDao.findPlayerThatHasJoinedMultipleGames(gameIdInt);
            if (!assholes.isEmpty()) {
                for (int i = 0; i < assholes.size(); i++) {
                    gameDao.removePlayer(gameIdInt, assholes.get(i));
                }
            }
            gameDao.updateGameState(gameIdInt, "ongoing");
        }
        request.setAttribute("game_id", gameIdInt);
        request.setAttribute("username", userName);
        request.getRequestDispatcher("/move_player").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // TODO: Do something here... Post new content? -Or perhaps quit to startpage

        String content = request.getParameter("newContent"); // The new content which shall be sent to the DB

    }
}
