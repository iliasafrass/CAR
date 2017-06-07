package car.tp4.servlet;

import car.tp4.entity.BookBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Afrass on 26/04/2017.
 */


@WebServlet("/initBooks")
public class initBooksServlet extends HttpServlet {
    @EJB
    private BookBean bookBean;



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        bookBean.initBooks();

        request.setAttribute("books", bookBean.getAllBooks());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/initBooks.jsp");
        dispatcher.forward(request, response);
    }
}
