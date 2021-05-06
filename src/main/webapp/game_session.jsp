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

    function add_dice_head_row(table){
        var dice_header_row = "<tr scope='row'>" +
            "<th scope='col'>Dice 1</th>" +
            "<th scope='col'>Dice 2</th>" +
            "<th scope='col'>Dice 3</th>" +
            "<th scope='col'>Dice 4</th>" +
            "<th scope='col'>Dice 5</th>" + "</th>"

        table.empty()
        table.append(dice_header_row)
    }
    function add_game_head_row(table, active_player, player_amount, game_data){
        var game_header_row = "<tr scope='row'>" +
            "<th scope='col'>Round:" +game_data.current_round +"</th>" +
            "<th scope='col'>Host: " + game_data.host+ "</th>"
        var players = [game_data.p2, game_data.p3, game_data.p4, game_data.p5 ]
        for (var player_index in players ){
            game_header_row = game_header_row +
                "<th scope='col'> " + players[player_index] + "</th>"

        }
        game_header_row = game_header_row +
            "<th scope='col'>Active player is " + active_player + "</th>"

        table.empty()
        table.append(game_header_row)
    }

    function add_dice_row(table, active_player, dice_1, dice_2, dice_3, dice_4, dice_5){
        var username = "<%=request.getAttribute("username")%>"
        if(username == "null"){
            username = "<%=request.getParameter("username")%>"
        }

        var dice_row = "<tr scope='row'>"


        if (dice_1 < 0){
            dice_row = dice_row + "<td>" + "[" + Math.abs(dice_1).toString() + "]" + "</td>"
        } else{
            dice_row = dice_row + "<td>" + dice_1.toString() + "</td>"
        }
        if (dice_2 < 0){
            dice_row = dice_row + "<td>" + "[" + Math.abs(dice_2).toString() + "]" + "</td>"
        } else{
            dice_row = dice_row + "<td>" + dice_2.toString() + "</td>"
        }
        if (dice_3 < 0){
            dice_row = dice_row + "<td>" + "[" + Math.abs(dice_3).toString() + "]" + "</td>"
        } else{
            dice_row = dice_row + "<td>" + dice_3.toString() + "</td>"
        }
        if (dice_4 < 0){
            dice_row = dice_row + "<td>" + "[" + Math.abs(dice_4).toString() + "]" + "</td>"
        } else{
            dice_row = dice_row + "<td>" + dice_4.toString() + "</td>"
        }
        if (dice_5 < 0){
            dice_row = dice_row + "<td>" + "[" + Math.abs(dice_5).toString() + "]" + "</td>"
        } else{
            dice_row = dice_row + "<td>" + dice_5.toString() + "</td>"
        }




        if (active_player === username){

            function make_button(button_name, param_name, param_value){
                var game_id = "<%=request.getAttribute("game_id")%>"
                if(game_id == "null"){
                    game_id = "<%=request.getParameter("game_id")%>"
                }
                var username = "<%=request.getAttribute("username")%>"
                if(username == "null"){
                    username = "<%=request.getParameter("username")%>"
                }

                var button = "<form action='http://localhost:8080/WebYatzy-0.0.2/game' method='post'>" +
                    "<input type='hidden' value=" + username + " name='username'>" +
                    "<input type='hidden' value=" + game_id + " name='game_id'>"
                if(param_value == null) {
                    button = button + '<button class="btn btn-primary btn-sm" type="submit" name=' + param_name + ' >' + button_name + '</button>' +'</form>'
                } else{
                    button = button + '<button class="btn btn-primary btn-sm" type="submit" name=' + param_name + ' value='+param_value+'>' + button_name + '</button>' +'</form>'
                }
                return button

            }

            let roll_button = '<button class="btn btn-primary btn-sm" type="submit" name="is_roll" >Re-roll </button>'
            let finish_button = '<button class="btn btn-primary btn-sm" type="submit" name="is_done">End Turn </button>'
            let username_param =   "<input type='hidden' value=" + username  + " name='username'>"
            var game_id = "<%=request.getAttribute("game_id")%>"
            if(game_id == "null"){
                game_id = "<%=request.getParameter("game_id")%>"
            }
            let game_id_param =             "<input type='hidden' value=" + game_id + " name='game_id'>"
            dice_row = dice_row + "<td>" + make_button("Re-Roll", "is_roll", null) + "</td>"
            dice_row = dice_row + "<td>" + make_button("End Turn", "is_done", null) + "</td>" +"</tr>"
            table.append(dice_row)

            var dice_select_row =  "<tr scope='row'>"
            let dice_param = "dice_1"

            if (dice_1 > 0){
                dice_select_row = dice_select_row + "<td>" + make_button("Keep", dice_param, "keep") + "</td>"
            } else{
                dice_select_row = dice_select_row + "<td>"  + make_button("Put Back", dice_param, "put_back") + "</td>"
            }

            dice_param = "dice_2"
            if (dice_2 > 0){
                dice_select_row = dice_select_row + "<td>" + make_button("Keep", dice_param, "keep") + "</td>"
            } else{
                dice_select_row = dice_select_row + "<td>"  + make_button("Put Back", dice_param, "put_back") + "</td>"
            }


            dice_param = "dice_3"
            if (dice_3 > 0){
                dice_select_row = dice_select_row + "<td>" + make_button("Keep", dice_param, "keep") + "</td>"
            } else{
                dice_select_row = dice_select_row + "<td>"  + make_button("Put Back", dice_param, "put_back") + "</td>"
            }

            dice_param = "dice_4"
            if (dice_4 > 0){
                dice_select_row = dice_select_row + "<td>" + make_button("Keep", dice_param, "keep") + "</td>"
            } else{
                dice_select_row = dice_select_row + "<td>"  + make_button("Put Back", dice_param, "put_back") + "</td>"
            }

            dice_param = "dice_5"
            if (dice_5 > 0){
                dice_select_row = dice_select_row + "<td>" + make_button("Keep", dice_param, "keep") + "</td>"
            } else{
                dice_select_row = dice_select_row + "<td>"  + make_button("Put Back", dice_param, "put_back") + "</td>"
            }
            dice_select_row = dice_select_row  + "</tr>"
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
        add_game_head_row(game_table, game_map.active_player, game_map.player_amount, game_map)
        add_dice_head_row(dice_table)
        for(var round_index in game_map.rounds) {
            var game_row = "<tr scope='row'>"
            for (var index in game_map.rounds[round_index]) {

                console.log(game_map.rounds[round_index][index])
                game_row = game_row + "<td>" + game_map.rounds[round_index][index] + "</td>"


            }

            /*
            var game_row =  "<tr scope='row'>" +
                "<td>" + round[0] + "</td>"
            for (var i = 1; i <= game_map.player_amount+1; i++){
                game_row = game_row + "<td>" + round[i]+ "</td>"
            }*/
            game_row = game_row + "</tr>"
            game_table.append(game_row)
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
        var game_id = "<%=request.getAttribute("game_id")%>"

        if(game_id == "null"){
            game_id = "<%=request.getParameter("game_id")%>"
        }
        data = {"game_id":game_id}
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

<h1 class="display-4">Yatzy Game #<%=request.getParameter("game_id")%></h1>
<h6>Velkommmen <span style="font-weight: bold"><%=request.getParameter("username")%></span></h6>


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
