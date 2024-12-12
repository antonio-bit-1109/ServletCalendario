<%--
  Created by IntelliJ IDEA.
  User: CORSO_JJ09
  Date: 12/12/2024
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifica Appuntamento</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>

<form action="../../CalendarioServlet/ModificaAppuntamento" method="post">

    <input type="hidden" name="idApp" id="idApp" value="${requestScope.idApp}">
    <input type="hidden" name="giorno" id="giorno" value="${requestScope.giorno}">
    <input type="hidden" name="mese" id="mese" value="${requestScope.mese}">
    <input type="hidden" name="anno" id="anno" value="${requestScope.anno}">

    <label for="nuovaOra">nuovo orario</label>
    <input type="time" name="nuovaOra" id="nuovaOra" required>

    <label for="nuovaDescrizione">descrizione</label>
    <input type="text" name="nuovaDescrizione" id="nuovaDescrizione" required>

    <button type="submit"> Invia modifiche</button>

</form>

</body>
</html>
