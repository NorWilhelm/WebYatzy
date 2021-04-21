package servlets;

import com.google.gson.Gson;
import dao.GameDao;
import dao.ScoreCardDao;
import dao.UserDao;
import model.Game;
import model.ScoreCard;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "lobbyList", value = "/lobbies")
public class LobbyListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private GameDao gameDao;



    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        List<Game> all_games = gameDao.findAll();
        // Pre Games
        List<Game> pre_games = all_games.stream()
                .filter(p_g -> p_g.getGamestate().equals("pre_game"))
                .collect(Collectors.toList());
        Map<String, Map<String, String> > pre_games_map = new HashMap<>();
        for(Game game : pre_games){
            Map<String, String> game_map = new HashMap<>();
            game_map.put("game_id", game.getGame_id().toString() );
            game_map.put("active_player", game.getActive_player());
            game_map.put("current_round", game.getCurrent_round().toString() );
            game_map.put("dice1", game.getDice1().toString() );
            game_map.put("dice2", game.getDice2().toString() );
            game_map.put("dice3", game.getDice3().toString() );
            game_map.put("dice4", game.getDice4().toString() );
            game_map.put("dice5", game.getDice5().toString() );
            game_map.put("username_host", game.getUsername_host() );
            game_map.put("username_p2", game.getUsername_p2() );
            game_map.put("username_p3", game.getUsername_p3() );
            game_map.put("username_p4", game.getUsername_p4() );
            game_map.put("username_p5", game.getUsername_p5() );
            game_map.put("gamestate", game.getGamestate() );
            game_map.put("host_scid", game.getHost_scid().toString() );
            game_map.put("player_2_scid", game.getPlayer_2_scid().toString() );
            game_map.put("player_3_scid", game.getPlayer_3_scid().toString() );
            game_map.put("player_4_scid", game.getPlayer_4_scid().toString() );
            game_map.put("player_5_scid", game.getPlayer_5_scid().toString() );
            pre_games_map.put(game.getGame_id().toString(), game_map);
        }

    /*    // Ongoing Games
        List<Game> ongoing_games = all_games.stream()
                .filter(p_g -> p_g.getGamestate().equals("ongoing_game"))
                .collect(Collectors.toList());
        Map<String, Map<String, String> > ongoing_games_map = new HashMap<>();
        for(Game game : ongoing_games){
            Map<String, String> game_map = new HashMap<>();
            game_map.put("game_id", game.getGame_id().toString() );
            game_map.put("active_player", game.getActive_player());
            game_map.put("current_round", game.getCurrent_round().toString() );
            game_map.put("dice1", game.getDice1().toString() );
            game_map.put("dice2", game.getDice2().toString() );
            game_map.put("dice3", game.getDice3().toString() );
            game_map.put("dice4", game.getDice4().toString() );
            game_map.put("dice5", game.getDice5().toString() );
            game_map.put("username_host", game.getUsername_host() );
            game_map.put("username_p2", game.getUsername_p2() );
            game_map.put("username_p3", game.getUsername_p3() );
            game_map.put("username_p4", game.getUsername_p4() );
            game_map.put("username_p5", game.getUsername_p5() );
            game_map.put("gamestate", game.getGamestate() );
            game_map.put("host_scid", game.getHost_scid().toString() );
            game_map.put("player_2_scid", game.getPlayer_2_scid().toString() );
            game_map.put("player_3_scid", game.getPlayer_3_scid().toString() );
            game_map.put("player_4_scid", game.getPlayer_4_scid().toString() );
            game_map.put("player_5_scid", game.getPlayer_5_scid().toString() );
            ongoing_games_map.put(game.getGame_id().toString(), game_map);
        }*/

      /*  // Finished Games
        List<Game> post_games = all_games.stream()
                .filter(p_g -> p_g.getGamestate().equals("post_game"))
                .collect(Collectors.toList());
        Map<String, Map<String, String> > post_games_map = new HashMap<>();
        for(Game game : post_games){
            Map<String, String> game_map = new HashMap<>();
            game_map.put("game_id", game.getGame_id().toString() );
            game_map.put("active_player", game.getActive_player());
            game_map.put("current_round", game.getCurrent_round().toString() );
            game_map.put("dice1", game.getDice1().toString() );
            game_map.put("dice2", game.getDice2().toString() );
            game_map.put("dice3", game.getDice3().toString() );
            game_map.put("dice4", game.getDice4().toString() );
            game_map.put("dice5", game.getDice5().toString() );
            game_map.put("username_host", game.getUsername_host() );
            game_map.put("username_p2", game.getUsername_p2() );
            game_map.put("username_p3", game.getUsername_p3() );
            game_map.put("username_p4", game.getUsername_p4() );
            game_map.put("username_p5", game.getUsername_p5() );
            game_map.put("gamestate", game.getGamestate() );
            game_map.put("host_scid", game.getHost_scid().toString() );
            game_map.put("player_2_scid", game.getPlayer_2_scid().toString() );
            game_map.put("player_3_scid", game.getPlayer_3_scid().toString() );
            game_map.put("player_4_scid", game.getPlayer_4_scid().toString() );
            game_map.put("player_5_scid", game.getPlayer_5_scid().toString() );
            post_games_map.put(game.getGame_id().toString(), game_map);
        }*/


        // Pack Game Data
       /* Map<String, Map<String, Map<String, String> >> response_data_map = new HashMap<>();
        response_data_map.put("pre_games", pre_games_map);
        response_data_map.put("ongoing_games", ongoing_games_map);
        response_data_map.put("post_games", post_games_map);*/
        String json = new Gson().toJson(pre_games_map);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);



    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {




    }
}