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
    <title>WebYatzy - Lobby</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>

<!-- <body onload="updateLobbyList()"> -->
<body style="margin: 20px">

<script>
    function join_lobby_button(game_id){
        var username = "<%=request.getAttribute("username")%>"
        var action_button =  "<form action='http://localhost:8080/WebYatzy-0.0.2/join_lobby' method='get'>" +
            "<input type='hidden' value=" + username + " name='username'>" +
                "<input type='hidden' value=" + game_id + " name='game_id'>" +
                    '<button class="btn btn-primary btn-sm" type="submit">Join Game</button>' +
        '</form>'
        return action_button

    }
    function add_lobby_row(table, game_id, host, p2, p3, p4, p5, btn) {
        new_row = "<tr scope='row'>" +
            "<td>" + game_id + "</td>" +
            "<td>" + host + "</td>" +
            "<td>" + p2 + "</td>" +
            "<td>" + p3 + "</td>" +
            "<td>" + p4 + "</td>" +
            "<td>" + p5 + "</td>" +
            "<td>" + btn + "</td>" +
            "</tr>"
        table.append(new_row)
    }
        function add_head_row(table){
            headerRow = "<tr scope='row'>" +
            "<th scope='col'>Game ID</th>" +
            "<th scope='col'>Host</th>" +
            "<th scope='col'>Player 2</th>" +
            "<th scope='col'>Player 3</th>" +
            "<th scope='col'>Player 4</th>" +
            "<th scope='col'>Player 5</th>" +
            "<th scope='col'>Action</th>" + "</tr>"

            table.empty()
            table.append(headerRow)
    }
    function syncView(preGames) {

        table = $("#tableId tbody")
        if ($("#tableId tbody").length == 0) {
            $("#tableId").append("<tbody></tbody>");
        }

        add_head_row(table)

        preGames.forEach(lobby => {

        var host = lobby.username_host
        var p2 = lobby.username_p2
        var p3 = lobby.username_p3 // TODO: Check if null, then show join-button
        var p4 = lobby.username_p4
        var p5 = lobby.username_p5
        var game_id = lobby.game_id
        var client_username = "<%=request.getAttribute("username")%>"

        if (host == client_username) {
            var client_action_button = " <button class='btn btn-primary btn-sm' type='button' value='kek'>Start game</button>"
        }
        else if ([p2, p3, p4, p5].includes(client_username) ){
            var client_action_button = " <button class='btn btn-primary btn-sm' type='button' value='kek'>Leave game</button>"
        } else {
            var client_action_button = join_lobby_button(game_id)
        }
          add_lobby_row(table, game_id, host, p2, p3, p4, p5, client_action_button)

        })
    }
    async function sleep(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

   function retrieve_db_data(responseDataJSON) {
        let preGames = []

        // TODO: Send the "global" list instead of .pre_games (sends to syncView)
        for (var lobby in responseDataJSON.pre_games)
            preGames.push(responseDataJSON.pre_games[lobby])

        //for (var lobby in responseDataJSON.ongoing_games)
        //    ongoingGames.push([lobby, responseDataJSON.ongoing_games[lobby]])

        // Perhaps not check if null, but just return when done.
        console.log("preGames: ")
        console.log(preGames)
        syncView(preGames)


   }
        // Get lobbies
      async function getLobbies() {
           /* while(true) {*/
                $.get("http://localhost:8080/WebYatzy-0.0.2/lobbiesServlet", retrieve_db_data)
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
        getLobbies()

</script>

    <h1 class="display-4">Lobby</h1>
    <h6>Velkommmen <span style="font-weight: bold"><%=request.getAttribute("username")%></span></h6>

        <form action="http://localhost:8080/WebYatzy-0.0.2/create_lobby" method="get">
            <input type="hidden" value="<%=request.getAttribute("username")%>" name="username">
            <input type="hidden" value="<%=request.getAttribute("game_id")%>" name="game_id">
            <button class="btn btn-primary btn-sm" type="submit">Create Game</button>
        </form>
        <button class="btn btn-primary btn-sm" onclick="print(${preGames})">Print games to PDF</button>


    <!-- TODO: If getAttr("username") is username_host, then... -->

    <div id="testViewId"></div>
    <button onclick="getLobbies()"></button>

    <table class="table" id="tableId">

        <tr id="singleRowId">
        </tr>
  <%--      <c:forEach var="lobby" items="${preGames}">
            <c:forEach var="item" items="lobby">
                <tr scope="row">
                    <p>${item[1].active_player}</p>
                </tr>
            </c:forEach>
        </c:forEach>--%>
    </table>


</body>

</html>
