package car.tp4.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

@WebServlet("/jsp/add")
public class BookServletAdd extends HttpServlet{

	  @EJB
	  private BookBean bookBean;

	  @Override
	  public void init() throws ServletException {
	    //bookBean.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien"));
	  }

	  @Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	    String title = request.getParameter("titre");
	    String author = request.getParameter("auteur");
	    int date = Integer.parseInt(request.getParameter("date"));
	    
	    Book book = new Book(author, title, date, 10);
	    
	    PrintWriter out = response.getWriter();
	    bookBean.addBook(book);
	    out.println(book.toString()+" est bien ajouter");
	    request.setAttribute("books", bookBean.getAllBooks());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/book.jsp");
		dispatcher.forward(request, response);
	    
	  }
}


