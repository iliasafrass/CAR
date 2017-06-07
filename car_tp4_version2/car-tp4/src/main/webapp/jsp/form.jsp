<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" media="screen" type="text/css"  href="form.css"/>
</head>
<body>
    <form action="add" method="post">
    <div>
        <label for="titre">Titre :</label>
        <input type="text" name ="titre" id="titre" />
    </div>
    <div>
        <label for="auteur ">Auteur :</label>
        <input type="text" name ="auteur" id="auteur" />
    </div>
    <div>
        <label for="annee de parution">année de parution :</label>
        <input type="text" name ="date" id="date" />
    </div>
   
    <div class="button">
        <button type="submit">Valider</button>
    </div>
</form>
</body>
</html>