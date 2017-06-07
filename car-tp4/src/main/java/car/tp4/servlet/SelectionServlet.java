package car.tp4.servlet;

/**
 * Created by Afrass on 26/04/2017.
 */

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;
import car.tp4.entity.Panier;
import car.tp4.entity.PanierBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/selection")
public class SelectionServlet extends HttpServlet{



    public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {

        HttpSession session = req.getSession();

        List<Book> booksPanier = (List<Book>) session.getAttribute("booksPanier");



        req.setAttribute("booksP",booksPanier);



        this.getServletContext().getRequestDispatcher("/jsp/selection.jsp").forward(req, rep);
    }
}
