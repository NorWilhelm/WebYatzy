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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ScoreCardDao scoreCardDao;
    @EJB
    private GameDao gameDao;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        Integer game_id = Integer.parseInt((String)request.getAttribute("game_id"));
        List<Game> games = gameDao.findAll();

        Game target_game = null;
        for(Game game : games){
            if (game.getGame_id().equals(game_id)){
                target_game = game;
                break;
            }
        }
        Map<String, Map<String, String>> response_data_map = new HashMap<>();

        List<ScoreCard> score_cards = new ArrayList<>();
        Integer host_scid = target_game.getHost_scid();
        score_cards.add(scoreCardDao.findScoreCard(host_scid));

                Integer player_2_scid = target_game.getPlayer_2_scid();
        if (player_2_scid != null ){
            score_cards.add( scoreCardDao.findScoreCard(player_2_scid));
        }
        Integer player_3_scid = target_game.getPlayer_3_scid();
        if (player_3_scid != null ){
            score_cards.add( scoreCardDao.findScoreCard(player_3_scid));
        }
        Integer player_4_scid = target_game.getPlayer_4_scid();
        if (player_4_scid != null ){
            score_cards.add( scoreCardDao.findScoreCard(player_4_scid));
        }
        Integer player_5_scid = target_game.getPlayer_5_scid();
        if (player_5_scid != null ){
            score_cards.add( scoreCardDao.findScoreCard(player_5_scid));
        }



    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // TODO: Do something here... Post new content? -Or perhaps quit to startpage

        String content = request.getParameter("newContent"); // The new content which shall be sent to the DB

    }
}