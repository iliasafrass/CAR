package car.tp4.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book implements Serializable{
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;
  private String author;
  private String title;
  private int date;
  private int quantite;

  public Book() {
	 }
  
  public Book(String author, String title, int date) {
	    this.author = author;
	    this.title = title;
	    this.date = date;
	   
	  }
  
  public Book(String author, String title, int date, int quantite) {
    this.author = author;
    this.title = title;
    this.date = date;
    this.quantite = quantite;
  }

  public long getId() {
	    return id;
	  }

  
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public int getDate() {
	    return date;
	  }
  
  public int getQuantite() {
	    return quantite;
	  }

  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void setdate(int date) {
	    this.date = date;
	  }
  
  public void setQuantite(int quantite){
	    this.quantite = quantite;
	  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    if (id != book.id) return false;
    if (!author.equals(book.author)) return false;
    return title.equals(book.title);
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + author.hashCode();
    result = 31 * result + title.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Book{" +
      "author='" + author + '\'' +
      ", title='" + title + '\'' +
      ", date= '"+ date+'\''+
       ", quantite= '"+ quantite+'\''+
      '}';
  }
}
