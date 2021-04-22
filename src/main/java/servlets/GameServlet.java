package servlets;

import dao.*;
import model.Game;
import model.ScoreCard;
import model.User;


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
        Integer dice1 = (Integer) request.getAttribute("dice1");
        Integer dice2 = (Integer) request.getAttribute("dice2");
        Integer dice3 = (Integer) request.getAttribute("dice3");
        Integer dice4 = (Integer) request.getAttribute("dice4");
        Integer dice5 = (Integer) request.getAttribute("dice5");
        Integer game_id = (Integer) request.getAttribute("game_id");
        boolean isDone = (boolean) request.getAttribute("isDone");

        gameDao.updateDice(game_id, dice1, dice2, dice3, dice4, dice5);
        if (isDone){
            Game game = gameDao.findGame(game_id);

//            String old_active =

        }

        /*int turns = 1;
        int numberOfPickedDice = 0;
        int result;
        HttpSession session = request.getSession();
        GameDaoImpl gameDao = new GameDaoImpl();    //I propose to merge interface and Impl classes into a static class
        Game game = gameDao.findGame((int) session.getAttribute("gameId"));  //or whatever way we can get the id of an ongoing game we are interested in
        String activePlayerString = game.getActive_player();
        UserDaoImpl userDao = new UserDaoImpl();
        while ((turns <= 3) && (numberOfPickedDice < 5)) {
            Integer dice1 = (Integer) request.getAttribute("dice1");
            Integer dice2 = (Integer) request.getAttribute("dice2");
            Integer dice3 = (Integer) request.getAttribute("dice3");
            Integer dice4 = (Integer) request.getAttribute("dice4");
            Integer dice5 = (Integer) request.getAttribute("dice5");
            Integer[] diceArray = {dice1, dice2, dice3, dice4, dice5};
            game.setCurrent_round(turns);
            for (int i = 0; i < 5; i++) {
                if (diceArray[i] != null) {
                    switch (i) {
                        case (1):
                            game.setDice1(diceArray[i]);
                            gameDao.updateGameDice1(game.getGame_id(), game.getDice1());
                        case (2):
                            game.setDice2(diceArray[i]);
                            gameDao.updateGameDice2(game.getGame_id(), game.getDice2());
                        case (3):
                            game.setDice3(diceArray[i]);
                            gameDao.updateGameDice3(game.getGame_id(), game.getDice3());
                        case (4):
                            game.setDice4(diceArray[i]);
                            gameDao.updateGameDice4(game.getGame_id(), game.getDice4());
                        case (5):
                            game.setDice5(diceArray[i]);
                            gameDao.updateGameDice5(game.getGame_id(), game.getDice5());
                    }
                    numberOfPickedDice++;
                }
            }
            //Hopefully, we deal with null/0 values in the class below
            result = Util.calculateScore(turns, game.getDice1(), game.getDice2(), game.getDice3(), game.getDice4(), game.getDice5());
            request.setAttribute("resultRound" + turns, result);
            userDao.updateUserScoreCard(result, activePlayerString);    //that was pretty confusing, needs to be double checked
            turns++;

        }
        RequestDispatcher rd = request.getRequestDispatcher("Any page that proceeds the game further");
        rd.forward(request, response);*/


    }
}