<%--
  Created by IntelliJ IDEA.
  User: sebastian
  Date: 21.04.2021
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebYatzy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>

<!-- <body onload="updateLobbyList()"> -->
<body style="margin: 20px">

<script>

    function add_dice_head_row(table, active_player){
        var dice_header_row = "<tr scope='row'>" +
            "<th scope='col'>Dice 1</th>" +
            "<th scope='col'>Dice 2</th>" +
            "<th scope='col'>Dice 3</th>" +
            "<th scope='col'>Dice 4</th>" +
            "<th scope='col'>Dice 5</th>" + "</th>"

        table.empty()
        table.append(dice_header_row)
    }
    function add_game_head_row(table, active_player, player_amount){
        game_header_row = "<tr scope='row'>" +
            "<th scope='col'>Round</th>" +
            "<th scope='col'>Host</th>"
        for (var i = 2; i <= player_amount; i++){
            game_header_row = game_header_row +
                "<th scope='col'>Player " + i + "</th>"

        }
        game_header_row = game_header_row +
            "<th scope='col'>Active player is " + active_player + "</th>"

        table.empty()
        table.append(game_header_row)
    }

    function add_dice_row(table, active_player, dice_1, dice_2, dice_3, dice_4, dice_5){

        var username = "<%=request.getAttribute("username")%>"
        var dice_row = "<tr scope='row'>"
        for(let [index, dice] in [dice_1, dice_5, dice_2, dice_3, dice_4].entries()){
            if (dice.length == 2){
                dice_row = dice_row + "<td>" + "[" + dice.slice(1) + "]" + "</td>"
            } else
            {
                dice_row = dice_row + "<td>" + dice + "</td>"
            }
        }

        if (active_player === username){

            let roll_button = '<button class="btn btn-primary btn-sm" type="submit" >Re-roll </button>'
            let finish_button = '<button class="btn btn-primary btn-sm" type="submit" name="is_done">End Turn </button>'
            let username_param =   "<input type='hidden' value=" +username  + " name='username'>"
            let game_id =  "<%=request.getAttribute("game_id")%>"
            let game_id_param =             "<input type='hidden' value=" + game_id + " name='game_id'>"
            dice_row = dice_row + '<form action="http://localhost:8080/WebYatzy-0.0.2/game" method="post">' + game_id_param + username_param + "<td>" + roll_button + "</td>" + "<td>" + finish_button + "</tr>"
            table.append(dice_row)

            var dice_select_row =  "<tr scope='row'>"
            for(let [index, dice] in [dice_1, dice_5, dice_2, dice_3, dice_4].entries()){
                let dice_param = "dice_" + index
                if (dice.length == 2){
                    dice_select_row = dice_select_row + "<td>" + '<input type="checkbox" id=' + dice_param + ' name=' + dice_param + ' value=' + dice_param  + 'checked >' + "</td>"
                } else
                {
                    dice_select_row = dice_select_row + "<td>" + '<input type="checkbox" id=' + dice_param + ' name=' + dice_param + ' value=' + dice_param + ' >' + "</td>"
                }
            }
            dice_select_row = dice_select_row + "</form>" + "</tr>"
            table.append(dice_select_row)

        }
        else {
            dice_row = dice_row + "</tr>"
            table.append(dice_row)
        }
    }


    function syncView(game_map) {

        game_table = $("#game_table tbody")
        if ($("#game_table tbody").length == 0) {
            $("#game_table").append("<tbody></tbody>");
        }
        dice_table = $("#dice_table tbody")
        if ($("#dice_table tbody").length == 0) {
            $("#dice_table").append("<tbody></tbody>");
        }
        add_game_head_row(game_table, game_map.active_player, game_map.player_amount)
        add_dice_head_row(dice_table)
        for(let [index, round] in game_map.rounds.entries()){
            let game_row =  "<tr scope='row'>" +
                "<td>" + round[0] + "</td>"
            for (var i = 1; i <= game_map.player_amount+1; i++){
                game_row = game_row + "<td>" + round[i] + "</td>"
            }
            game_row = game_row + "</tr>"
        }

        add_dice_row(dice_table, game_map.active_player, game_map.dice_1, game_map.dice_2, game_map.dice_3, game_map.dice_4, game_map.dice_5)


    }
    async function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    function retrieve_db_data(responseDataJSON) {
       // TODO add check if state of game changed. ie new dice throw / score
        syncView(responseDataJSON)


    }
    // Get lobbies
    function getLobbies() {
        /* while(true) {*/
        data = {"game_id":"<%=request.getAttribute("game_id")%>"}
        $.get("http://localhost:8080/WebYatzy-0.0.2/game", data , retrieve_db_data)
        /*      await sleep(2000)
          }*/

    }
    /*function updateLobbyList() {
         console.log("updateLobbyList is running...")
        $.get("http://localhost:8080/WebYatzy-0.0.2/lobbiesServlet", function(responseDataJSON) {
             console.log(responseDataJSON)
                              // Find all child elements with tag name "option" and remove them (just to prevent duplicate options when button is pressed again).
            $.each(responseDataJSON, function(key, value) {
                console.log("outer :" +key);
                console.log("outerasdasdas :"+value);
                $.each(value, function(key, value) {
                    console.log("inner :" + key);
                    console.log("inner :" + value);} );
                // Create HTML <option> element, set its value with currently iterated key and its text content with currently iterated item and finally append it to the <select>.
            })
        });
        console.log("test");
    }*/
    /* $(document).on("click", "#loadLobbies", function() {
         $.get("http://localhost:8080/WebYatzy-0.0.2/lobbies", function(responseLobbies) {
             console.log(responseLobbies)
         })
     })*/

    // window.onload = updateLobbyList()
    var intervalID = window.setInterval(getLobbies, 500);


</script>

<h1 class="display-4">Lobby</h1>
<h6>Velkommmen <span style="font-weight: bold"><%=request.getAttribute("username")%></span></h6>


<!-- TODO: If getAttr("username") is username_host, then... -->

<div id="testViewId"></div>
<button onclick="getLobbies()"></button>

<table class="table" id="game_table">

</table>
<br/>
<table class="table" id="dice_table">

</table>

</body>

</html>
