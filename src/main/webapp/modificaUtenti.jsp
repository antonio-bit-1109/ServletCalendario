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
<body class="flex flex-col">

<h1>Login effettuato con successo, Benvenuto ${sessionScope.get("user").username}</h1>

<main class="flex flex-col">


    <div>
        <h4 class="font2 m0">crea nuovo utente</h4>

        <form class="flex flex-col align-start" action="create" method="post">
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
        <h4 class="font2 m0">modifica password del tuo account </h4>
        <form class="flex flex-col align-start" action="editPswUser" method="post">
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
        <h4 class="font2 m0">cancella utente</h4>
        <form class="flex flex-col align-start" action="delete" method="post">
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
</main>

<aside>
    <a href="http://localhost:8080/CalendarioServlet/login">Indietro</a>
</aside>

</body>
</html>
