package car.tp4.servlet;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Afrass on 26/04/2017.
 */



@WebServlet("/home")
public class HomeServlet extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, rep);
    }

}


