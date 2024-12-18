<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.*" %>
<%--import libreria jstl per tag invece delle scriptlet--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

    <title>
        Calendario <c:out value="${requestScope.meseString}"/> - <c:out value="${requestScope.anno}"/>
    </title>

    <%--    foglio css--%>
    <link rel="stylesheet" href="style/style.css">
    <script src="${pageContext.request.contextPath}/Javascript/ChangeSfondoMeseCal.js"></script>
    <script src="${pageContext.request.contextPath}/Javascript/GetAppuntamenti.js"></script>
</head>

<body class="flex">
<main>


    <h1 class="title m0">
        <c:out value="${requestScope.meseString}"/>
    </h1>

    <h2 class="sub-title m0">
        <c:out value="${requestScope.anno}"/>
    </h2>

    <div class="p1-5 bg-grey">

        <table>
            <thead>
            <tr>
                <th class="cell font2  m2-inline p1">
                    <c:out value="Lun"/>
                </th>
                <th class="cell font2  m2-inline p1">
                    <c:out value="Mart"/>
                </th>
                <th class="cell font2  m2-inline p1">
                    <c:out value="Merc"/>
                </th>
                <th class="cell font2  m2-inline p1">
                    <c:out value="Giov"/>
                </th>
                <th class="cell font2  m2-inline p1">
                    <c:out value="Ven"/>
                </th>
                <th class="cell font2  m2-inline p1">
                    <c:out value="Sab"/>
                </th>
                <th class="cell font2  m2-inline p1">
                    <c:out value="Dom"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${requestScope.matrix}">
                <tr>
                    <c:forEach var="n" items="${row}">
                        <c:choose>
                            <c:when test="${n == 0}">
                                <td class="cell font2 m2-inline p1"></td>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${not empty requestScope.densitaImpegniMese[n]}">
                                    <c:set var="className">
                                        <c:choose>
                                            <c:when test="${requestScope.densitaImpegniMese[n] == 1}">easyDay</c:when>
                                            <c:when test="${requestScope.densitaImpegniMese[n] == 2}">midDay</c:when>
                                            <c:when test="${requestScope.densitaImpegniMese[n] > 2}">busyDay</c:when>
                                            <%--<c:otherwise>default-class</c:otherwise>--%>
                                        </c:choose>
                                    </c:set>

                                    <td class="cell font2 m2-inline p1 ${className}">
                                        <a href="<c:url value='http://localhost:8080/CalendarioServlet/MostraAppuntamenti'>
                                    <c:param name="mese" value="${requestScope.mese}"/>
                                    <c:param name="anno" value="${requestScope.anno}"/>
                                    <c:param name="giorno" value="${n}"/>
                                         </c:url>"> ${n}</a>
                                    </td>

                                </c:if>
                                <c:if test="${empty requestScope.densitaImpegniMese[n]}">

                                    <td class="cell font2 m2-inline p1">
                                        <a href="<c:url value='http://localhost:8080/CalendarioServlet/MostraAppuntamenti'>
                                        <c:param name="mese" value="${requestScope.mese}"/>
                                        <c:param name="anno" value="${requestScope.anno}"/>
                                        <c:param name="giorno" value="${n}"/>
                                         </c:url>"> ${n} </a>
                                    </td>

                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>

<div class="flex flex-col "
     style="justify-content: center; background-color: grey; align-self: center; margin-inline-start: 1em">

    <div class="p1 align-center" style="padding: 1em">
        <a style="font-size: 1.5em" type="button"
           href="<c:url value='http://localhost:8080/CalendarioServlet/generaCale'>

          <c:choose>
                <c:when test="${requestScope.mese == 12}">
                    <c:param name='mese' value='${1}' />
                    <c:param name='anno' value='${requestScope.anno + 1}' />
                </c:when>
               <c:otherwise>
                     <c:param name='mese' value='${requestScope.mese + 1}' />
                     <c:param name='anno' value='${requestScope.anno}' />
                </c:otherwise>
          </c:choose>

        </c:url>">mese successivo</a>
    </div>

    <div class="p1" style="padding: 1em">

        <a style="font-size: 1.5em" type="button"
           href="<c:url value='http://localhost:8080/CalendarioServlet/generaCale'>
           <c:choose>
                <c:when test="${requestScope.mese == 1}">
                    <c:param name='mese' value='${12}' />
                    <c:param name='anno' value='${requestScope.anno - 1}' />
                </c:when>
               <c:otherwise>
                     <c:param name='mese' value='${requestScope.mese - 1}' />
                     <c:param name='anno' value='${requestScope.anno}' />
                </c:otherwise>
          </c:choose>
        </c:url>">mese precedente</a>

    </div>


</div>


<div style="background-color: grey" class="p1-5 fit-content">
    <a style="font-size: 1.5em" href="login">
        torna indietro</a>
</div>


<%--    div per la visualizzazione degli appuntamenti del giorno su cui ho fatto hover --%>

<div class="flex align-center">
    <table class="bg-grey" border="2">
        <thead id="thead-temp-table">
        <tr>

        </tr>
        </thead>
        <tbody id="tbody-temp-table">
        <tr>

        </tr>
        </tbody>
    </table>
</div>


</body>
</html>

