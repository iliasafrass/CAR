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
import java.util.*;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

  @EJB
  private BookBean bookBean;


  @Override
  public void init() throws ServletException {
    bookBean.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien","1995"));
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

      response.setContentType("text/html;charset-UTF-8");

      String commands[] =  request.getParameterValues("checkboxNamesList");

      HttpSession session = request.getSession();

      List<Book> booksPanier = new ArrayList<Book>();
      //String[] commands = {"2","3"};

      if(commands !=null && commands.length!=0) {
          for (String i : commands) {
              Book b = bookBean.getBookWihid(i);
              booksPanier.add(b);
          }

          session.setAttribute("booksPanier", booksPanier);


      }


      request.setAttribute("books",bookBean.getAllBooks());

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/book.jsp");
    dispatcher.forward(request, response);
  }

}
