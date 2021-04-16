<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Registering</title>
        <!-- This page serves as login site as well as register page for a new user. These two forms should be side-by-side or by pressing a button for switching between them  -->
        <!-- TODO: Add CSS/JS for displaying these forms in a pretty and intuitive way. -->
    </head>
    <body>
    <form action="register" method="post">
        <fieldset>
            <legend>Persondata</legend>
            <p>Username: <input type="text" name="username"/>/>
            <p>Password: <input type="password" name="password"/>/>
            <p><input type="submit" value="Registrer" />/>
        </fieldset>
    </form>


    <form action="login" method="post">
        <fieldset>
            <legend>Persondata</legend>
            <p>Username: <input type="text" name="username"/>/>
            <p>Password: <input type="password" name="password"/>/>
            <p><input type="submit" value="Login" />/>
        </fieldset>
    </form>

    </body>
</html>