
<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>

    <link type="text/css" rel="stylesheet" href="/jsp/css/page.css" />
    <link rel="script"  href="/jsp/js/page.js">
</head>
<body>

<section>
    <!--for demo wrap-->
    <h1>Auteurs des livres : </h1>
    <div class="tbl-header">
        <table cellpadding="0" cellspacing="0" border="0">
            <thead>
            <tr>
                <th>Auteur</th>
                </tr>
            </thead>
        </table>
    </div>
    <%
        Collection<String> auteurs = (Collection<String>) request.getAttribute("auteurs");
    %>


    <div class="tbl-content">
        <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
            <%for (String auteur: auteurs) {%>
            <tr>
                <td><%=auteur%></td>
            </tr>

            <%
                }
            %>
            </tbody>
        </table>
    </div>

</section>

<form id="waterform" method="get" action="/home" >
    <input type="submit" value="Retour à la page d'acceuil" />
</form>


<!-- follow me template -->
<div class="made-with-love">
    Made  by
    <a target="_blank" >Afrass Ilias</a>
</div>

</body>
</html>
