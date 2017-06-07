package car.tp4.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.BookBean;
@WebServlet("/authors")
public class BookServletAuthors extends HttpServlet {

	  @EJB
	  private BookBean bookBean;

	  @Override
	  public void init() throws ServletException {
	    //bookBean.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien","ddd"));
	  }

	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		  List<String> authors = new ArrayList<String>();
			authors = bookBean.getAllAuthors();
			PrintWriter out = response.getWriter();
			for(String a : authors)
				out.println("<h3>author : "+ a + "</h3>");
	   
	   
	  }
	}