<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Sebastian
  Date: 22.03.2021
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <c:forEach var="person" items="${requestScope.persons}">
        <h2>${person}</h2>
    </c:forEach>
</head>
<body>

</body>
</html>


