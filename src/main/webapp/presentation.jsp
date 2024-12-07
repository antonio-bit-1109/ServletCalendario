<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.*" %>
<!DOCTYPE html>
<html>
<head>

    <%
        String meseString = (String) request.getAttribute("meseString");
        int anno = (int) request.getAttribute("anno");

    %>

    <title>Calendario <%= anno + " " + meseString %>
    </title>
    <link rel="stylesheet" href="style.css"/>
</head>
<body>


<%
    int[][] matrix = (int[][]) request.getAttribute("matrix");

    if (matrix == null) {
        response.sendError(404, "matrice è null");
    }

    StringBuilder sb = new StringBuilder();

    sb.append("<center>");
    sb.append(" <h1 style='font-size:3em;margin:0em;'>").append(meseString).append("</h1>");
    sb.append("<h2 style='font-size:2em;'>").append(anno).append("</h2>");
    sb.append("<table>\n" +
            "    <thead>\n" +
            "        <tr>\n" +
            "            <th class=\"cell font2  m2-inline p1\">Lun</th>\n" +
            "            <th class=\"cell font2  m2-inline p1\">Mart</th>\n" +
            "            <th class=\"cell font2  m2-inline p1\">Merc</th>\n" +
            "            <th class=\"cell font2  m2-inline p1\">Giov</th>\n" +
            "            <th class=\"cell font2  m2-inline p1\">Ven</th>\n" +
            "            <th class=\"cell font2  m2-inline p1\">Sab</th>\n" +
            "            <th class=\"cell font2  m2-inline p1\">Dom</th>\n" +
            "        </tr>\n" +
            "    </thead>\n");

    sb.append("<tbody>");
    for (int i = 0; i < matrix.length; i++) {
        sb.append("<tr>");
        for (int j = 0; j < matrix[i].length; j++) {

            if (matrix[i][j] == 0) {
                sb.append("<td class=\"cell font2 m2-inline p1\" >").append(" ").append("</td>");

            } else {

                sb.append("<td class=\"cell font2 m2-inline p1\" >").append(matrix[i][j]).append("</td>");

            }

        }
        sb.append("</tr>");
    }
    sb.append("</tbody>");
    sb.append("</table>");
    sb.append("</center>");
    out.println(sb.toString());
%>

</body>
</html>

<%--<script>--%>
<%--    import {sfondoFetch} from "./sfondoFetch";--%>

<%--    sfondoFetch("rome");--%>
<%--</script>--%>