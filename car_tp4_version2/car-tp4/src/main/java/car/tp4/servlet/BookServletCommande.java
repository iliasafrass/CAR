package car.tp4.servlet;

import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import car.tp4.entity.BookBean;
import car.tp4.entity.*;
import car.tp4.entity.PanierBean;
@WebServlet("/panier")
public class BookServletCommande extends HttpServlet {

	  @EJB
	  private PanierBean panierBean;
	  @EJB
	  private BookBean bookBean;
	  
	  @Override
	  public void init() throws ServletException {
	    //bookBean.addBook(new Book("The Lord of the Rings", "J. R. R. Tolkien","ddd"));
	  }

	  @Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
			
		HttpSession as = request.getSession();
		String idClient = (String) as.getAttribute("id_client");
		HashMap<String, String> quantites = new HashMap<String, String>();
		quantites = (HashMap<String, String>) as.getAttribute("quantites");
		
		HashMap<String, List<car.tp4.entity.Book>> panier = (HashMap<String, List<car.tp4.entity.Book>>) as.getAttribute("panier");
		
		PrintWriter out = response.getWriter();
		if(panier != null){
			panierBean.addPanier(new Panier(idClient, panier.get(idClient)));
			String str = bookBean.deleteBooks(panier.get(idClient), quantites);
			out.println("<h3>"+str+"</h3>");
			out.print("<h3>Commande Valider</h3>");
		}else{ 
			out.println("<h3>"+panierBean.panierVide()+"</h3>");
		}
	  }
	}