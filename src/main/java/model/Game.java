package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "test_schema", name="games")
@NamedQuery(name = "Game.findAll", query = "select t from Game t")
@Entity
public class Game {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer game_id;

    private String active_player;
    private Integer current_round;
    private Integer dice1;
    private Integer dice2;
    private Integer dice3;
    private Integer dice4;
    private Integer dice5;

    private String username_host;
    private String username_p2;
    private String username_p3;
    private String username_p4;
    private String username_p5;

    private String gamestate;

    private Integer host_scid;
    private Integer player_2_scid;
    private Integer player_3_scid;
    private Integer player_4_scid;
    private Integer player_5_scid;

    public Game () {

    }

    public Game(String active_player, Integer current_round, Integer dice1, Integer dice2, Integer dice3, Integer dice4, Integer dice5, String username_host, String username_p2, String username_p3, String username_p4, String username_p5, String gamestate, Integer host_scid, Integer player_2_scid, Integer player_3_scid, Integer player_4_scid, Integer player_5_scid) {
        super();
        this.active_player = active_player;
        this.current_round = current_round;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.dice3 = dice3;
        this.dice4 = dice4;
        this.dice5 = dice5;
        this.username_host = username_host;
        this.username_p2 = username_p2;
        this.username_p3 = username_p3;
        this.username_p4 = username_p4;
        this.username_p5 = username_p5;
        this.gamestate = gamestate;
        this.host_scid = host_scid;
        this.player_2_scid = player_2_scid;
        this.player_3_scid = player_3_scid;
        this.player_4_scid = player_4_scid;
        this.player_5_scid = player_5_scid;
    }

    public Game(String username_host) {
        super();
        this.active_player = username_host;
        this.current_round = null;
        this.dice1 = null;
        this.dice2 = null;
        this.dice3 = null;
        this.dice4 = null;
        this.dice5 = null;
        this.username_host = username_host;
        this.username_p2 = null;
        this.username_p3 = null;
        this.username_p4 = null;
        this.username_p5 = null;
        this.gamestate = "pre_game";
        this.host_scid = null;
        this.player_2_scid = null;
        this.player_3_scid = null;
        this.player_4_scid = null;
        this.player_5_scid = null;
    }

    public List<String> getPlayers(){
        List<String> players = new ArrayList<>();
        if(!username_host.equals(null))
            players.add(username_host);
        if(!username_p2.equals(null))
            players.add(username_p2);
        if(!username_p3.equals(null))
            players.add(username_p3);
        if(!username_p4.equals(null))
            players.add(username_p4);
        if(!username_p5.equals(null))
            players.add(username_p5);
        return players;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public String getActive_player() {
        return active_player;
    }

    public void setActive_player(String active_player) {
        this.active_player = active_player;
    }

    public Integer getCurrent_round() {
        return current_round;
    }

    public void setCurrent_round(Integer current_round) {
        this.current_round = current_round;
    }

    public Integer getDice1() {
        return dice1;
    }

    public void setDice1(Integer dice1) {
        this.dice1 = dice1;
    }

    public Integer getDice2() {
        return dice2;
    }

    public void setDice2(Integer dice2) {
        this.dice2 = dice2;
    }

    public Integer getDice3() {
        return dice3;
    }

    public void setDice3(Integer dice3) {
        this.dice3 = dice3;
    }

    public Integer getDice4() {
        return dice4;
    }

    public void setDice4(Integer dice4) {
        this.dice4 = dice4;
    }

    public Integer getDice5() {
        return dice5;
    }

    public void setDice5(Integer dice5) {
        this.dice5 = dice5;
    }

    public String getUsername_host() {
        return username_host;
    }

    public void setUsername_host(String username_host) {
        this.username_host = username_host;
    }

    public String getUsername_p2() {
        return username_p2;
    }

    public void setUsername_p2(String username_p2) {
        this.username_p2 = username_p2;
    }

    public String getUsername_p3() {
        return username_p3;
    }

    public void setUsername_p3(String username_p3) {
        this.username_p3 = username_p3;
    }

    public String getUsername_p4() {
        return username_p4;
    }

    public void setUsername_p4(String username_p4) {
        this.username_p4 = username_p4;
    }

    public String getUsername_p5() {
        return username_p5;
    }

    public void setUsername_p5(String username_p5) {
        this.username_p5 = username_p5;
    }

    public String getGamestate() {
        return gamestate;
    }

    public void setGamestate(String gamestate) {
        this.gamestate = gamestate;
    }

    public Integer getHost_scid() {
        return host_scid;
    }

    public void setHost_scid(Integer host_scid) {
        this.host_scid = host_scid;
    }

    public Integer getPlayer_2_scid() {
        return player_2_scid;
    }

    public void setPlayer_2_scid(Integer player_2_scid) {
        this.player_2_scid = player_2_scid;
    }

    public Integer getPlayer_3_scid() {
        return player_3_scid;
    }

    public void setPlayer_3_scid(Integer player_3_scid) {
        this.player_3_scid = player_3_scid;
    }

    public Integer getPlayer_4_scid() {
        return player_4_scid;
    }

    public void setPlayer_4_scid(Integer player_4_scid) {
        this.player_4_scid = player_4_scid;
    }

    public Integer getPlayer_5_scid() {
        return player_5_scid;
    }

    public void setPlayer_5_scid(Integer player_5_scid) {
        this.player_5_scid = player_5_scid;
    }

    @Override
    public String toString() {
        return "GameMetadata{" +
                "game_id=" + game_id +
                ", active_player='" + active_player + '\'' +
                ", current_round=" + current_round +
                ", dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", dice3=" + dice3 +
                ", dice4=" + dice4 +
                ", dice5=" + dice5 +
                ", username_host='" + username_host + '\'' +
                ", username_p2='" + username_p2 + '\'' +
                ", username_p3='" + username_p3 + '\'' +
                ", username_p4='" + username_p4 + '\'' +
                ", username_p5='" + username_p5 + '\'' +
                ", gamestate='" + gamestate + '\'' +
                ", host_scid=" + host_scid +
                ", player_2_scid=" + player_2_scid +
                ", player_3_scid=" + player_3_scid +
                ", player_4_scid=" + player_4_scid +
                ", player_5_scid=" + player_5_scid +
                '}';
    }
}
