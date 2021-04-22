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
    <script>
        function syncView(preGames) {
            // TODO: Update the table with data from getLobbies()
            // $("#testView").innerHTML("test")
        }

        // Get lobbies
        function getLobbies() {
            console.log("kjører getLobbies")

            var $testVariabel = $("#testViewId")
            $testVariabel.find("option").remove()
            $("<option>").val("test").text("hest").appendTo($testVariabel)


                $.get("http://localhost:8080/WebYatzy-0.0.2/lobbiesServlet", function(responseDataJSON) {

                    var preGames = []
                    for (var lobby in responseDataJSON.pre_games)
                        preGames.push(responseDataJSON.pre_games[lobby])

                    //for (var lobby in responseDataJSON.ongoing_games)
                    //    ongoingGames.push([lobby, responseDataJSON.ongoing_games[lobby]])

                    // Perhaps not check if null, but just return when done.
                    console.log("Ferdig med async")
                    console.log("preGames: ")
                    console.log(preGames)

                    syncView(preGames)
                })
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
        window.onload = getLobbies()

    </script>
</head>

<!-- <body onload="updateLobbyList()"> -->
<body style="margin: 20px">

    <h1 class="display-4">Lobby</h1>
    <h6>Velkommmen <span style="font-weight: bold"><%=request.getAttribute("username")%></span></h6>

        <form action="http://localhost:8080/WebYatzy-0.0.2/create_lobby" method="get">
            <input type="hidden" value="<%=request.getAttribute("username")%>" name="username">
            <button class="btn btn-primary btn-sm" type="submit">Create Game</button>
        </form>
        <button class="btn btn-primary btn-sm" onclick="print(${preGames})">Print games to PDF</button>


    <!-- TODO: If getAttr("username") is username_host, then... -->

    <div id="testViewId"></div>

    <table class="table">
        <tr scope="row">
            <th scope="col">Active Player</th>
            <th scope="col">Host user</th>
            <th scope="col">Current Round</th>
            <th scope="col">Action</th>
        </tr>
        <%--<c:forEach var="lobby" items="${preGames}">
            <c:forEach var="item" items="lobby">
                <tr scope="row">
                    <p>${item[1].active_player}</p>
                </tr>
            </c:forEach>
        </c:forEach>--%>
    </table>

    <button class="btn btn-primary btn-sm" type="button" value="${lobby.game_id}">Join game</button>
</body>
</html>
