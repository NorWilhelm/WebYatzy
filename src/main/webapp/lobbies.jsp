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
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            $.get("http://localhost:8080/WebYatzy-0.0.2/game", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                console.log(responseText)
                $("#somediv").text(responseText)           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
            })
        })

        function updateLobbyList() {
            console.log("updateLobbyList is running...")
            $("#testDiv").text("Laster...")
            // TODO:
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
        }
       /* $(document).on("click", "#loadLobbies", function() {
            $.get("http://localhost:8080/WebYatzy-0.0.2/lobbies", function(responseLobbies) {
                console.log(responseLobbies)
                $("#lobbyDiv").text(responseLobbies)
            })
        })*/


        async function kek() {

            console.log("Success with loading")
            await new Promise(r => setTimeout(r, 1000));
            console.log("Success with loading")
            await new Promise(r => setTimeout(r, 1000));
            console.log("Success with loading")
            await new Promise(r => setTimeout(r, 1000));
            console.log("Success with loading")
            await new Promise(r => setTimeout(r, 1000));

        }

        window.onload = updateLobbyList()


    </script>
</head>

<body onload="updateLobbyList">

    <h1>Lobby</h1>
    <div id="testDiv"></div>
<%--    <button id="loadLobbies">Load Games</button>--%>

    <form action="http://localhost:8080/WebYatzy-0.0.2/new_pre_game" method="get">

            <input type="hidden" value="<%=request.getAttribute("username")%>" name="username">
            <button type="submit">Create Game</button>
    </form>
    <button onclick="print(${pre_games})">load games (not working)</button>

    <div id="lobbyDiv"></div>

    <table>
        <tr>
            <th>Game ID</th>
            <th>Active Player</th>
            <th>Current Round</th>
            <th>[USERNAME]</th>
        </tr>
        <c:forEach var="game" items="${pre_games}">
            <tr>
                <p>${game.gameID}</p>
                <button type="button" value="${game.gameID}">Join game</button>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
