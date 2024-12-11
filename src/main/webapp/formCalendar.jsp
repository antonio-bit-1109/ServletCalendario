<%--
  Created by IntelliJ IDEA.
  User: CORSO_JJ09
  Date: 11/12/2024
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserisci dati calendario </title>
    <link rel="stylesheet" href="style/style.css"/>
</head>
<body>

<h3> Benvenuto ${sessionScope.user.username}</h3>

<h2>Calcola calendario</h2>
<form action="generaCale" method="get">
    <div style="margin: 1em">
        <label style="color: black" for="mese">mese</label>
        <input type="number" name="mese" id="mese" required>
    </div>

    <div style="margin: 1em">
        <label style="color: black" for="anno">anno</label>
        <input type="number" name="anno" id="anno" required>
    </div>
    <button type="submit"> ricava calendario</button>
</form>

<div>
    <a href="modificaUtenti.jsp"> modifica utenti</a>
</div>

</body>
</html>
