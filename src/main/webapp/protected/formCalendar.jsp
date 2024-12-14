<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css"/>
</head>
<body>

<h3> Benvenuto ${sessionScope.user.username}</h3>

<h2>Calcola calendario</h2>
<form action="generaCale" method="get">
    <div style="margin: 1em">
        <label style="color: black" for="mese">mese</label>
        <input type="number" name="mese" id="mese" required>
    </div>
    <div>
        <c:if test="${requestScope.ErrorMese != null}">
            <p class="c-red"> ${requestScope.ErrorMese}</p>
            <c:remove var="ErrorMese" scope="request"/>
            <audio src="./audios/scream1.mp3" autoplay></audio>
        </c:if>
    </div>

    <div style="margin: 1em">
        <label style="color: black" for="anno">anno</label>
        <input type="number" name="anno" id="anno" required>
    </div>
    <div>
        <c:if test="${requestScope.ErrorAnno != null}">
            <p class="c-red"> ${requestScope.ErrorAnno}</p>
            <c:remove var="ErrorAnno" scope="request"/>
            <audio src="./audios/scream2.mp3" autoplay></audio>
        </c:if>
    </div>
    <button type="submit"> ricava calendario</button>
</form>

<div>
    <a href="/CalendarioServlet/modificaUtenti.jsp"> modifica utenti</a>
</div>

</body>
</html>
