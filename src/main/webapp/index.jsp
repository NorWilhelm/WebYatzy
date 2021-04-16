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
  <a href="/login">Login / create user</a>
  <a href="/yatzy">Start new game </a> <!-- Points to the servlet who creates a new game -->
    <ul>
        <c:forEach var="game" items="${requestScope.games}">
          <li>/li> <!-- TODO: Get data for current games from websocket? -->
        </c:forEach>
    </ul>
  </body>
</html>
