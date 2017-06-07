<%@ page import="car.tp4.entity.Book" %>
<%--
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

<h1>       Server is running!</h1>
<div id="form">

    <div class="fish" id="fish"></div>
    <div class="fish" id="fish2"></div>
    <form id="waterform" method="get" action="/home" >


        <div class="formgroup" id="name-form">
            <label name="titre">Vous avez reussi d'executer votre projet :)</label>
        </div>


            <input type="submit" value="Acceder à la page d'acceuil" />

    </form>


</div>

<div class="made-with-love">
    Made  by
    <a target="_blank" >Afrass Ilias</a>
</div>

</html>






