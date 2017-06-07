
<%@ page import="car.tp4.entity.Book" %><%--
  Created by IntelliJ IDEA.
  User: Afrass
  Date: 25/04/2017
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:set var="root" value="${pageContext.request.contextPath}"/>

    <link type="text/css" rel="stylesheet" href="/jsp/css/home.css" />
    <link rel="script"  href="/jsp/js/home.js">

</head>

<h1>Bibliotheque de livres</h1>
<div id="form">

    <div class="fish" id="fish"></div>
    <div class="fish" id="fish2"></div>
    <form id="waterform" method="get" action="/books">


        <div class="formgroup" id="name-form">
            <label name="titre">Vous avez Bien initialisé votre bibliothque de livres</label>
        </div>

        <input type="submit" value="Voir votre Bibliotheque de livres" />


    </form>


</div>

<div class="made-with-love">
    Made  by
    <a target="_blank" >Afrass Ilias</a>
</div>

</html>






