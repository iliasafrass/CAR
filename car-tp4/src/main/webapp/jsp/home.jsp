<%@ page import="car.tp4.entity.Book" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: Afrass
  Date: 25/04/2017
  Time: 17:33
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
    <form id="waterform" method="post" action="/addbook" >

        <div class="formgroup" id="name-form">
            <label name="titre">Titre*</label>
            <input type="text" id="name" name="titreText" />
        </div>

        <div class="formgroup" id="email-form">
            <label name="nomAutheur">Nom Auteur*</label>
            <input type="text" id="idNomAuteur" name="auteurText" />
        </div>

        <div class="formgroup" id="message-form">
            <label name="annee">Annee de parution*</label>
            <input type="text" id="idannee" name="anneeText" />
        </div>

        <input type="submit" value="ajouter" />
    </form>


    <form id="waterform" method="post" action="/initBooks" >
        <input type="submit" value="initialiser votre bibliotheque de livres" />
    </form>

    <form id="waterform" method="get" action="/auteurs" >
        <input type="submit" value="voir la liste des auteurs" />
    </form>

</div>

<div class="made-with-love">
    Made  by
    <a target="_blank" >Afrass Ilias</a>
</div>

</html>


