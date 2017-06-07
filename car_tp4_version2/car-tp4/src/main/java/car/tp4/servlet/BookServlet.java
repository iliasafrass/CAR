package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;
import car.tp4.entity.PanierBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

  @EJB
  private BookBean bookBean;
  @EJB
  private PanierBean panierBean;

  @Override
  public void init() throws ServletException {
    //bookBean.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien","ddd"));
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	
	
	HttpSession as = request.getSession();
	HashMap<String, String> quantites = new HashMap<String, String>();
	
	
	String[] commands = request.getParameterValues("checkboxNamesList");
	String quantite  = request.getParameter("nemberOfBook");
	String sort = request.getParameter("sort");
	
	if(commands != null){
		 HashMap<String, List<Book>> panier = new HashMap<String, List<Book>>(); 
		 panier.put(as.getId(), bookBean.getBookByTitle(commands));
		 request.setAttribute("commands", bookBean.getBookByTitle(commands));
		 //quantité pour chaque book
		 for(Book book : panier.get(as.getId())){
			 quantites.put(book.getTitle(),request.getParameter(book.getTitle()));
		 }
		 as.setAttribute("id_client", as.getId());
		 as.setAttribute("panier",panier );
		 as.setAttribute("quantites", quantites);
		
	}
	if(sort != null)
		request.setAttribute("books", bookBean.getBookSortByDate());
	else
		request.setAttribute("books", bookBean.getAllBooks());
   
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/book.jsp");
    dispatcher.forward(request, response);
   
  }
}
