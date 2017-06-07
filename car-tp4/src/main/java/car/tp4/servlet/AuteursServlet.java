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

@WebServlet("/auteurs")
public class AuteursServlet  extends HttpServlet {
    @EJB
    private BookBean bookBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("auteurs", bookBean.getListAuthors());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/auteurs.jsp");
        dispatcher.forward(request, response);
    }

}
