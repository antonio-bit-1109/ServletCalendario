<%--
  Created by IntelliJ IDEA.
  User: CORSO_JJ09
  Date: 10/12/2024
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>login Effettuato con successo</title>
    <link rel="stylesheet" href="style/style.css"/>
</head>
<body>

<h1>Login effettuato con successo, Benvenuto ${sessionScope.get("nomeUtente")}</h1>

<div>
    <h4>crea nuovo utente</h4>

    <form action="create" method="post">
        <label for="creauser"> Inserisci username</label>
        <input type="text" name="creauser" id="creauser" required>

        <label for="creapass"> Inserisci password</label>
        <input type="password" name="creapass" id="creapass" required>

        <button type="submit"> crea nuovo utente</button>
    </form>
    <div>
        <c:choose>
            <c:when test="${requestScope.msgSucc != null}">
                <h6 class="red font1">${requestScope.msgSucc}</h6>
            </c:when>
        </c:choose>
    </div>
</div>

<div>
    <h4>modifica password del tuo account </h4>
    <form action="editPswUser" method="post">
        <label for="username"> Inserisci username</label>
        <input type="text" name="username" id="username" required>

        <label for="oldPass"> Inserisci vecchia password</label>
        <input type="password" name="oldPass" id="oldPass" required>

        <label for="newPass"> Inserisci nuova password</label>
        <input type="password" name="newPass" id="newPass" required>

        <button type="submit"> invia</button>
    </form>
    <div>
        <c:choose>
            <c:when test="${requestScope.editPswSucc != null}">
                <h6 class="red font1">${requestScope.editPswSucc}</h6>
            </c:when>
        </c:choose>
    </div>
</div>

<div>
    <h4>cancella utente</h4>
    <form action="delete" method="post">
        <label for="usernameDel"> Inserisci username</label>
        <input type="text" name="usernameDel" id="usernameDel" required>

        <label for="passDel"> Inserisci password</label>
        <input type="password" name="passDel" id="passDel" required>

        <button type="submit"> cancella utente</button>
    </form>
    <div>
        <c:choose>
            <c:when test="${requestScope.msgDeleteSucc != null}">
                <h6 class="red font1">${requestScope.msgDeleteSucc}</h6>
            </c:when>
        </c:choose>
    </div>
</div>

<div>
    <h4>recupera tutti gli utenti</h4>
    <form action="" method="get">

        <button type="submit"> recupera utenti</button>
    </form>
    <div>
        <c:choose>
            <c:when test="${requestScope.msgDeleteSucc != null}">
                <h6 class="red font1">${requestScope.msgDeleteSucc}</h6>
            </c:when>
        </c:choose>
    </div>
</div>


</body>
</html>
