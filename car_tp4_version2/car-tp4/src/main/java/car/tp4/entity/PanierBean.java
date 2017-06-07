package car.tp4.entity;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@Local
public class PanierBean {
	  @PersistenceContext(unitName = "panier-pu")
	  private EntityManager entityManager;
	  
	  @EJB
	  BookBean bookBean;
	  
	  public HashMap<String,  List<Book>> booksInPanier = new HashMap<String, List<Book>>();
	  
	  public void setBooksInPanier(HashMap<String, List<Book>> panier){
		  booksInPanier = panier;  
	  }

	public HashMap<String, List<Book>> getBooksInPanier() {
		return booksInPanier;
	}
	
	public void addPanier(Panier panier){
		entityManager.persist(panier);
	}
	
	public String panierVide(){
		return "Panier Vide !!";
	}
	  
	public Panier getPanierById(String id){
		 Query query = entityManager.createQuery("SELECT m FROM Panier as m WHERE m.idClient = :id");
		  query.setParameter("id", id);
		  return (Panier) query.getSingleResult();
	}
}
