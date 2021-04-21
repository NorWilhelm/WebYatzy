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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
      <title>StartPage</title>
      <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      <script>
          $(document).on("click", "#somebutton", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
              $.get("http://localhost:8080/WebYatzy-0.0.2/game", function(responseText) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                  console.log(responseText)
                  $("#somediv").text(responseText)           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.
              })
          })
      </script>

  </head>
  <body>
    <button id="somebutton">press here</button>
    <div id="somediv"></div>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/login" role="button">Login/create user</a>
    <a class="btn btn-primary" href="/yatzy" role="button">Start new game</a> <!-- Points to the servlet who creates a new game -->
  </body>
</html>
