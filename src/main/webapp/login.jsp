<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Registering</title>
        <!-- This page serves as login site as well as register page for a new user. These two forms should be side-by-side or by pressing a button for switching between them  -->
        <!-- TODO: Add CSS/JS for displaying these forms in a pretty and intuitive way. -->
    </head>
    <body>
    <form action="registrer" method="post">
        <fieldset>
            <legend>Persondata</legend>
            <p>Fornavn: <input type="text" name="fornavn" />/>
            <p>Etternavn: <input type="text" name="etternavn" />/>
            <p><input type="submit" value="Registrer" />/>
        </fieldset>
    </form>


    <form action="login" metod="post">
        <fieldset>
            <legend>Persondata</legend>
            <p>Username: <input type="text" name="username"/>/>
            <p>Password: <input type="passowrd" name="password"/>/>
            <p><input type="submit" value="Login" />/>
        </fieldset>
    </form>

    </body>
</html>