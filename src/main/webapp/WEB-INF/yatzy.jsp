<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Yatzy 🎲️</title>
    </head>
    <body>
        <p>Du er innlogget som ${username}</p>
        Din Score:
        <br />
        <!-- TODO: Add the other players score -->
        <h2>Work in progress...</h2>
        <table border="1">
            <tr>
                <th>Score</th>
                <th>Tall</th>
            </tr>

            <!-- TODO add current score -->
            <tr>
                <td>(kommer)</td>
            </tr>
        </table>
        <br />

        <form action="${YATZY_URL}" method="post">
            <fieldset>
                <legend>Trykk på knappen for å trille terningene.</legend>
                <p>
                    <input type="submit" value="rollDice" />
                </p>
            </fieldset>
        </form>
        <form action="${LOGOUT_URL}" method="get">
            <fieldset>
                <p>
                    <input type="submit" value="Logg ut" />
                </p>
            </fieldset>
        </form>
        <h3>Dice roll display:</h3>
        <p></p>
    </body>
</html>
