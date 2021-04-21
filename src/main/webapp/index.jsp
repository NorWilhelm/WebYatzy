<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
      <title>WebYatzy - Startpage</title>
      <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  </head>
  <body style="margin: 20px;">
    <h1>WebYatzy 🎲️</h1>
    <a class="btn btn-primary" href="<%=request.getContextPath()%>/login" role="button">Login/create user</a>
    <a class="btn btn-primary" href="/yatzy" role="button">Start new game</a> <!-- Points to the servlet who creates a new game -->
  </body>
</html>
