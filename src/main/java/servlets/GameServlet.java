package servlets;

import com.google.gson.Gson;
import dao.*;
import model.Game;
import model.ScoreCard;
import model.User;
import utility.Util;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    private ScoreCardDao scoreCardDao;
    @EJB
    private GameDao gameDao;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        Integer game_id = Integer.parseInt((String)request.getParameter("game_id"));


        Game game = gameDao.findGame(game_id);
        List<List> rounds = new ArrayList<>();
        Integer player_amount = game.playerAmount();
        Integer current_round = game.getCurrent_round();

        Map<String, Object> response_data_map = new HashMap<>();

        List<ScoreCard> score_cards = new ArrayList<>();
        Integer host_scid = game.getHost_scid();
        score_cards.add(scoreCardDao.findScoreCard(host_scid));

        Integer player_2_scid = game.getPlayer_2_scid();
        if (player_2_scid != null ){
            score_cards.add( scoreCardDao.findScoreCard(player_2_scid));
        }

        Integer player_3_scid = game.getPlayer_3_scid();
        if (player_3_scid != null ){
            score_cards.add( scoreCardDao.findScoreCard(player_3_scid));
        }

        Integer player_4_scid = game.getPlayer_4_scid();
        if (player_4_scid != null ){
            score_cards.add( scoreCardDao.findScoreCard(player_4_scid));
        }

        Integer player_5_scid = game.getPlayer_5_scid();
        if (player_5_scid != null ){
            score_cards.add( scoreCardDao.findScoreCard(player_5_scid));
        }


        for(int i = 1; i <= current_round.intValue(); i++){
            List<String> round = new ArrayList<>();
            round.add(Util.round_map.get(i));
            for(ScoreCard score_card :score_cards ){
                Integer round_score = score_card.getScoreFromMap(i);
                if (round_score == null){
                    continue;
                }
                round.add(round_score.toString());
            }
            rounds.add(round);

        }

        response_data_map.put("active_player", game.getActive_player());
        response_data_map.put("rounds", rounds);
        response_data_map.put("player_amount", player_amount.toString());
        response_data_map.put("dice_1", game.getDice1().toString());
        response_data_map.put("dice_2", game.getDice2().toString());
        response_data_map.put("dice_3", game.getDice3().toString());
        response_data_map.put("dice_4", game.getDice4().toString());
        response_data_map.put("dice_5", game.getDice5().toString());
        response_data_map.put("host", game.getUsername_host());
        response_data_map.put("p2", game.getUsername_p2());
        response_data_map.put("p3", game.getUsername_p3());
        response_data_map.put("p4", game.getUsername_p4());
        response_data_map.put("p5", game.getUsername_p5());
        response_data_map.put("current_round",Util.round_map.get(current_round));
        String json = new Gson().toJson(response_data_map);

        request.setAttribute("game_id", game.getGame_id().toString());
        String username = request.getParameter("username");
        request.setAttribute("username", username);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        Integer game_id = Integer.parseInt(request.getParameter("game_id"));
        String username = request.getParameter("username");

        Game game = gameDao.findGame(game_id);
        Integer current_round = game.getCurrent_round();


        Integer current_throw = game.getCurrent_throw();


        boolean is_done = (request.getParameter("is_done") != null);
        boolean is_roll = (request.getParameter("is_roll") != null);

        if( current_round ==7){
            // Todo:  calc bonus
            Integer scorecard_id = game.getScorecardFromUser(username);
            scoreCardDao.calcBonus(scorecard_id);
            gameDao.progressTurn(game_id);

        } else if( current_round==15){
            // Todo:  calc total
            Integer scorecard_id = game.getScorecardFromUser(username);
            scoreCardDao.calcTotal(scorecard_id);
            gameDao.progressTurn(game_id);
            request.setAttribute("game_id", game_id);
            request.setAttribute("username", username);
            request.getRequestDispatcher("lobbies.jsp").forward(request, response);

        } else if( is_done || current_throw==3){

            Integer dice_1 = Math.abs(game.getDice1());
            Integer dice_2 = Math.abs(game.getDice2());
            Integer dice_3 = Math.abs(game.getDice3());
            Integer dice_4 = Math.abs(game.getDice4());
            Integer dice_5 = Math.abs(game.getDice5());
            Integer score = Util.calculateScore(game.getCurrent_round(), dice_1, dice_2, dice_3, dice_4, dice_5);
            Integer scorecard_id = game.getScorecardFromUser(username);
            scoreCardDao.updateScore(scorecard_id, current_round, score);
            gameDao.progressTurn(game_id);

        } else if( is_roll) {

            gameDao.updateDiceThrow(game_id);
          /*  if(current_throw==2){
                Integer dice_1 = Math.abs(game.getDice1());
                Integer dice_2 = Math.abs(game.getDice2());
                Integer dice_3 = Math.abs(game.getDice3());
                Integer dice_4 = Math.abs(game.getDice4());
                Integer dice_5 = Math.abs(game.getDice5());
                Integer score = Util.calculateScore(game.getCurrent_round(), dice_1, dice_2, dice_3, dice_4, dice_5);
                Integer scorecard_id = game.getScorecardFromUser(username);
                scoreCardDao.updateScore(scorecard_id, current_round, score);
                gameDao.progressTurn(game_id);
            }*/
        } else{

            boolean dice_1_state_changed = (request.getParameter("dice_1") != null);

            if(dice_1_state_changed){
                System.out.println("DICE 1 param" + request.getParameter("dice_1"));
                boolean dice_selected = (request.getParameter("dice_1").equals("keep"));
                gameDao.updateDiceState1(game_id,dice_selected);
            }
            boolean dice_2_state_changed = (request.getParameter("dice_2") != null);
            if(dice_2_state_changed){
                System.out.println("DICE 2 param" + request.getParameter("dice_2"));
                boolean dice_selected = (request.getParameter("dice_2").equals("keep"));
                gameDao.updateDiceState2(game_id,dice_selected);
            }
            boolean dice_3_state_changed = (request.getParameter("dice_3") != null);
            if(dice_3_state_changed){
                boolean dice_selected = (request.getParameter("dice_3").equals("keep"));
                gameDao.updateDiceState3(game_id,dice_selected);
            }
            boolean dice_4_state_changed = (request.getParameter("dice_4") != null);
            if(dice_4_state_changed){
                boolean dice_selected = (request.getParameter("dice_4").equals("keep"));
                gameDao.updateDiceState4(game_id,dice_selected);
            }
            boolean dice_5_state_changed= (request.getParameter("dice_5") != null);
            if(dice_5_state_changed){
                boolean dice_selected = (request.getParameter("dice_5").equals("keep"));
                gameDao.updateDiceState5(game_id, dice_selected);
            }

        }

        request.setAttribute("game_id", game_id);
        request.setAttribute("username", username);
        request.getRequestDispatcher("game_session.jsp").forward(request, response);


    }
}