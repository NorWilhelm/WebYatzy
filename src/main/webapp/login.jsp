<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        <title>Registering</title>
        <!-- This page serves as login site as well as register page for a new user. These two forms should be side-by-side or by pressing a button for switching between them  -->
        <!-- TODO: Add CSS/JS for displaying these forms in a pretty and intuitive way. -->
    </head>
    <body style="margin: 20px">
    <form action="register" method="post" style="margin-bottom: 50px">
        <fieldset>
            <legend>New User</legend>
            <p>Username: <input type="text" name="username"/>
            <p>Password: <input type="password" name="password"/>
            <p><small class="text-muted">Passordet ditt er ikke trygt her.</small></p>
            <p><input type="submit" value="Registrer"/>
        </fieldset>
    </form>


    <form action="login" method="post">
        <fieldset>
            <legend>Log in</legend>
            <p>Username: <input type="text" name="username"/>
            <p>Password: <input type="password" name="password"/>
            <p><input type="submit" value="Login"/>
        </fieldset>
    </form>

    </body>
</html>