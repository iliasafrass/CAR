package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Stateless
@Local
public class BookBean {

  @PersistenceContext(unitName = "book-pu")
  private EntityManager entityManager;

  public void addBook(Book book) {
    entityManager.persist(book);
  }
  
  public String deleteBooks(List<Book> books, HashMap<String,String> quantites){
	  String rep = "walooooo";
	  for(Book book : books){
		  if(book.getQuantite() > 0){
			  Book b = entityManager.find(Book.class, book.getId());
			  b.setQuantite(b.getQuantite()- Integer.parseInt(quantites.get(b.getTitle())));
			  rep = String.valueOf(book.getQuantite());
		  }  
		  else{
			  entityManager.remove(entityManager.merge(book));
			   rep = book.getTitle()+" n'existe pas dans le stock dsl !!";
		  }
	  }
	  return rep;
  }
  

  public List<Book> getAllBooks() {
    Query query = entityManager.createQuery("SELECT m from Book as m");
    return query.getResultList();
  }
  
  public void init(){
	  Book b1 = new Book("William Faulkner", "Absalom, Absalom!", 1991, 10);
	  Book b2 = new Book("Aldous Huxley", "After Many a Summer Dies the Swan", 1995, 10);
	  Book b3 = new Book("Aldous Huxley", "All Passion Spent", 2017, 10);
	  Book b4 = new Book("Aldous Huxley", "Bury My Heart at Wounded Knee", 1990, 10);
	  Book b5 = new Book("O. Henry", "Carrion Comfort", 1993, 10);
	  Book b6 = new Book("O. Henry", "A Catskill Eagle", 2014, 10);
	  
	  addBook(b1);
	  addBook(b2);
	  addBook(b3);
	  addBook(b4);
	  addBook(b5);
	  addBook(b6);
}
  
  public List<String> getAllAuthors(){
	  List<Book> books = getAllBooks();
	  List<String> authors = new ArrayList<String>();
	  for(Book b : books){
		  authors.add(b.getAuthor());
	  }
	  return authors;
  }
  
  public List<Book> getBookByTitle(String[] titles){
	
	  List<Book> books = getAllBooks();
	  List<Book> result = new ArrayList<Book>();
	  
	  for(Book book : books){
		  for(String title  : titles){
			  if (book.getTitle().equals(title))
				  result.add(book);
		  }
	  }
	  return result;
  }
  
  
  public List<Book> getBookByParteTitle(String title){
	  Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.title LIKE :title");
	  query.setParameter("title", "%"+title+"%");
	  return query.getResultList();  
  }
  
  public List<Book> getBookOfAuthor(String author){
	  Query query = entityManager.createQuery("SELECT m FROM Book as m WHERE m.author = :author");
	  query.setParameter("author", author);
	  return query.getResultList();
  }
  
  public List<Book> getBookSortByDate(){
	  Query query = entityManager.createQuery("SELECT m FROM Book as m ORDER BY m.date ASC");
	  return query.getResultList();
  }
 
}