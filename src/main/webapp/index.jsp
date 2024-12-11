<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Calendario dinamico</title>
    <link rel="stylesheet" href="style/style.css"/>
</head>

<body class="d-flex flex-col">

<main class="input alt flex flex-col">
    <!--    nome della servlet che deve effettuare logica di business  nell action nome del tag -->

    <div class="flex flex-col">
        <h4>Effettua il login.</h4>
        <form action="login" method="post" class="flex flex-col" style="align-items: flex-start">
            <label for="username"> Inserisci username</label>
            <input type="text" name="username" id="username">

            <label for="password"> Inserisci password</label>
            <input type="password" name="password" id="password">
            <button type="submit"> invia</button>
        </form>
    </div>


</main>


</body>
</html>


