package car.tp4.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;



@WebServlet("/title")
public class BookServletTitle extends HttpServlet {

	  @EJB
	  private BookBean bookBean;

	  @Override
	  public void init() throws ServletException {
	    //bookBean.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien","ddd"));
	  }

	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");  
		PrintWriter out = response.getWriter();
		
		List<Book> books = new ArrayList<Book>();
		if(!title.isEmpty()){
			books = bookBean.getBookByParteTitle(title);

			out.println("<h2>Book with title like :"+title+"</h2>");
			for(Book book : books)
				out.println("<h4>"+book.toString()+"</h4>");
		}
		
		if(!author.isEmpty()){
			books = bookBean.getBookOfAuthor(author);
			out.println("<h2>Books Of author : "+author+"</h2>");
			for(Book book : books){
				out.println("<h4>"+book.toString()+"</h4>");
		}
		}
		
		
		
		
	  }
	}
