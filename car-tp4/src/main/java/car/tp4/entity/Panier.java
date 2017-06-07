package car.tp4.entity;

import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

@Entity
public class Panier {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Integer id;
    protected String customer;
    protected LinkedList<Book> books;


    public Panier(String cost) {
        this.customer = cost;
        this.books = new LinkedList<Book>();
    }

    public Panier(String customer, LinkedList<Book> books) {
        this.customer = customer;
        this.books = books;
    }

    public Integer getId(){
        return this.id;
    }
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }


    public LinkedList<Book> getBooks() {
        return books;
    }


    public void setBooks(LinkedList<Book> books) {
        this.books = books;
    }

    public void addBook(Book b){
        this.books.add(b);
    }

}