<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link rel="stylesheet" media="screen" type="text/css"  href="books.css"/>
    </head>
    <body>
        <h2>Existing books</h2>
        <form action="title" methode="get" >
        	Search with title : <input type="text" name="title" />
        	Search for Book of author : <input type="text" name="author" />
        	<input type="submit" value="Search" />
        </form>
        <form action="books" method="get">
        	<input type="hidden" name="sort" value="sort" >
        	<input type="submit" value="Sort By Date" />
        </form>
        <input type="button" value="initialiser" onclick="window.open('init')"/>
        <input type="button" value="Refresh" onclick="window.open('books')"/>
        
        <form action ="books" methode="post">
	        <table border = "1px">
	        	<tr>
	        		<th><b>Title</b></th>
	        		<th><b>Author</b></th>
	        		<th><b>Date</b></th>
	        		<th><b>Quantité</b></th>
	        	</tr>
	        <%
	            Collection<Book> books = (Collection<Book>) request.getAttribute("books");
	
	            for (Book book: books) {
	        %>
	        	<tr>
	        		<td><%=book.getTitle()%></td>
	        		<td><%=book.getAuthor()%></td>
	        		<td><%=book.getDate()%></td>
	        		<td><%=book.getQuantite() %></td>
	        		<td><input type="checkbox" name="checkboxNamesList" value="<%=book.getTitle()%>" /></td>
	        		<td><input type="number" name="<%=book.getTitle() %>" step="1"></td>
	        	</tr>
	        <%} %>
	        </table>
	        <input type="submit" value="Valider Commande"/>
        </form>
        <form action="panier" methode="post">
	        <table border="1">
	        <% Collection<Book> commandes = (Collection<Book>) request.getAttribute("commands");
	        	if(commandes != null){
	        		for(Book book : commandes){
	        		
	        %>
	        	<tr>
	        		<td name="book" ><b><%=book.getTitle()%></b></td>
	        	</tr>
	        <%	} 
	        		
	         }	%>	
	    	</table>
	    	<input type="submit" value="Valider Panier"/>
	    </form>    
        
        
       
       
    </body>
</html>
