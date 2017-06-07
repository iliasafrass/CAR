package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;

/**
 * Created by Afrass on 26/04/2017.
 */

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet {
    @EJB
    private BookBean bookBean;



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titre = request.getParameter("titreText");
        String auteur = request.getParameter("auteurText");
        String annee =  request.getParameter("anneeText");

        Book b = new Book(auteur, titre, annee);
        bookBean.addBook(b);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/page.jsp");
        dispatcher.forward(request, response);
    }

}
