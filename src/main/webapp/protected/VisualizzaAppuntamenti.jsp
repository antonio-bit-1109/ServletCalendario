<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: CORSO_JJ09
  Date: 11/12/2024
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Appuntamenti del giorno</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>

<a href="http://localhost:8080/CalendarioServlet/generaCale?mese=${param.get("mese")}&anno=${param.get("anno")}">
    torna indietro </a>

<%--se nella request è presente una chiave listaAppuntamenti allora renderizza una tabella--%>
<c:choose>
    <c:when test="${not empty requestScope.listaAppuntamenti}">
        <table style="margin-block: 2em" border="2">
            <thead>
            <tr>
                <th class="p1">data</th>
                <th class="p1">ora</th>
                <th class="p1">descrizione appuntamento</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="objApp" items="${requestScope.listaAppuntamenti}">
                <tr>
                    <td class="p1"><c:out value="${objApp.data}"/></td>
                    <td class="p1"><c:out value="${objApp.ora}"/></td>
                    <td class="p1"><c:out value="${objApp.descrizione}"/></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <h5 style="font-size: 2em; color: red"> non ci sono appuntamenti da mostrare</h5>
    </c:otherwise>
</c:choose>


<div style="background-color: grey; padding: 1em;width: fit-content">

    <h4>Aggiungi un appuntamento per oggi </h4>

    <form action="AggiungiAppuntamento" method="post" style="align-items: flex-start" class="flex flex-col">


        <%--            input nascosti per inviare alla servlet i valori della data--%>
        <%--            da utilizzare nel campo data e ora della tabella appuntamenti del db--%>
        <input type="hidden" name="mese" value="${param.mese}">
        <input type="hidden" name="anno" value="${param.anno}">
        <input type="hidden" name="giorno" value="${param.giorno}">


        <label for="inputOra">ora</label>
        <input type="time" name="inputOra" id="inputOra">

        <label for="inputDesc">descrizione</label>
        <input type="text" name="inputDesc" id="inputDesc">

        <button style="margin-block-start: 1em" type="submit"> Aggiungi appuntamento</button>
    </form>
</div>

</body>
</html>