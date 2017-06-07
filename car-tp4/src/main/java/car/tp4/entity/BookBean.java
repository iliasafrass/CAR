package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local
public class BookBean {

  @PersistenceContext(unitName = "book-pu")
  private EntityManager entityManager;

  public void init() {
    clear();
  }

  public void addBook(Book book) {
    entityManager.persist(book);
  }

  public List<Book> getAllBooks() {
    Query query = entityManager.createQuery("SELECT m from Book as m");
    return query.getResultList();
  }

  public void clear() {
    Query q = entityManager.createQuery("DELETE FROM Book");
    q.executeUpdate();
    entityManager.flush();
    entityManager.clear();
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

  public Book getBookWihid(String id) {
    Query q = entityManager.createQuery("SELECT b FROM Book b WHERE b.id = "+id);
    Book book = (Book) q.getSingleResult();
    return book;
  }


  public List<String> getListAuthors() {
    Query q = entityManager.createQuery("SELECT DISTINCT b.author FROM Book b");
    List<String> authors = q.getResultList();
    return authors;
  }

  public void initBooks() {
    clear();
    addBook(new Book("Fourny", "ENCYCLOPÉDIE", "2000"));
    addBook(new Book("Faïza ", "Guène", "2004"));
    addBook(new Book("Bernard ", "Dionne", "2001"));
    addBook(new Book("J.R.R", "Tolkien", "2000"));
  }

}