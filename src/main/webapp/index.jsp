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

<body>

<main class="input alt">
    <!--    nome della servlet che deve effettuare logica di business  nell action nome del tag -->
    <form action="generaCale" method="get">
        <div>
            <label style="color: black" for="mese">mese</label>
            <input type="number" name="mese" id="mese" required>
        </div>


        <div>
            <label style="color: black" for="anno">anno</label>
            <input type="number" name="anno" id="anno" required>
        </div>
        <button type="submit"> ricava calendario</button>
    </form>

</main>

</body>
</html>


