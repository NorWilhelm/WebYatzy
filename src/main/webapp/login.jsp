<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Login</title>
    </head>
    <body>
        <p>
            <font color="red">${loginMessage}</font>
        </p>
        <form action="login" method="post">
            <fieldset>
                <legend>Login</legend>
                <p>
                    Navn: <input type="text" name="username" />
                </p>
                <p>
                    <input type="submit" value="Logg inn" />
                </p>
            </fieldset>
        </form>
    </body>
</html>
