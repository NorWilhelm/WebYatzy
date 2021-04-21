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
</head>

<body>
    <h1>Lobby</h1>
    <ul> <!-- TODO: Replace with data from LobbyListServlet.java and create a new lobbyList.jsp -->
        <c:forEach var="game" items="${requestScope.games}">
            <li>
                <p><${game.gameId}</p>
                <button type="button" value="${game.gameId}">Join game</button>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
