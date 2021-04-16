<%--
  Created by IntelliJ IDEA.
  User: sebastian
  Date: 10.04.2021
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>StartPage</title>
  </head>
  <body>
  <a href="<%=request.getContextPath()%>/login">Login / create user</a>
  <a href="/yatzy">Start new game </a> <!-- Points to the servlet who creates a new game -->
    <ul> <!-- TODO: Get data for current games from websocket? -->
        <c:forEach var="game" items="${requestScope.games}">
          <li>
              <p><${game.gameId}</p>
              <!-- TODO: John Olav: Add button for joining that specific game with it's gameId (see login.jsp) -->
          /li>
        </c:forEach>
    </ul>
  </body>
</html>
