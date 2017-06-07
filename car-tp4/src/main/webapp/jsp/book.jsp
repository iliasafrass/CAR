
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
    <h1>Livres : </h1>

    <form id="waterform4" method="get" action="/books" >

    <div class="tbl-header">

        <table cellpadding="0" cellspacing="0" border="0">
            <thead>
            <tr>
                <th>id</th>
                <th>Auteur</th>
                <th>Titre</th>
                <th>Annee</th>
                <th>Selectionner</th>
            </tr>
            </thead>
        </table>
    </div>
    <%
        Collection<Book> books = (Collection<Book>) request.getAttribute("books");

        %>


        <div class="tbl-content">
        <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
            <form id="waterform3" method="post" action="/books" >
            <%for (Book book: books) {

            %>
            <tr>
                <td><%=book.getId()%></td>
                <td><%=book.getAuthor()%> </td>
                <td><%=book.getTitle()%></td>
                <td><%=book.getYear()%></td>



                <td><input name="checkboxNamesList"  type="checkbox" value=<%=book.getId()%> /></td>
            </tr>
            <%
                }
            %>

                <input type="submit" value="Ajouter au panier" />
            </form>
        </tbody>
        </table>
        </div>

</section>



<form id="waterform2" method="get" action="/home" >
    <input type="submit" value="Retour à la page d'acceuil" />
</form>

<form id="waterform21" method="get" action="/selection" >
    <input type="submit" value="Acceder au panier" />
</form>


<!-- follow me template -->
<div class="made-with-love">
    Made  by
    <a target="_blank" >Afrass Ilias</a>
</div>

</body>
</html>
