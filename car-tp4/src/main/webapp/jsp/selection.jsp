
<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@ page import="car.tp4.entity.Panier" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
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
    <h1>Livres : </h1>
    <div class="tbl-header">
        <table cellpadding="0" cellspacing="0" border="0">
            <thead>
            <tr>
                <th>id</th>
                <th>Auteur</th>
                <th>Titre</th>
                <th>Annee</th>
                <th>Supprimer</th>
            </tr>
            </thead>
        </table>
    </div>
    <%
        Collection<Book> books = (Collection<Book>) request.getAttribute("booksP");
    %>


    <div class="tbl-content">
        <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
            <%for (Book book: books) {%>
            <tr>
                <td><%=book.getId()%></td>
                <td><%=book.getAuthor()%> </td>
                <td><%=book.getTitle()%></td>
                <td><%=book.getYear()%></td>
                <td>
                    <input type="checkbox" id="cbox1"  name="checkbox1"  value=<%=book.getId()%>  />
                </td>

            </tr>

            <%
                }
            %>
            </tbody>
        </table>
    </div>

</section>

<form id="waterform" method="get" action="/home" >
    <input type="submit" value="Valider la commande" />
</form>


<!-- follow me template -->
<div class="made-with-love">
    Made  by
    <a target="_blank" >Afrass Ilias</a>
</div>

</body>
</html>
