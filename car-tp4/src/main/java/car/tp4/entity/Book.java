package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe représentant les livres dans l'application
 *
 * @author Afrass ilias
 *
 */

@Entity
public class Book {


  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;
  private String author;
  private String title;
  private String year;

  public Book() {
    this.author = "????";
    this.title = "????";
    this.year = "????";
  }

  /**
   * Constructeur
   * @param author l'auteur du livre
   * @param title le titre du livre
   * @param year l'année de paruption du livre
   */
  public Book(final String author, String title, String year) {
    this.author = author;
    this.title = title;
    this.year = year;
  }


  /**
   * @return the year
   */
  public String getYear() {
    return year;
  }

  /**
   * @param year the year to set
   */
  public void setYear(String year) {
    this.year = year;
  }

  /**
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @param author the author to set
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title  title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  public long getId() {
    return id;
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
/*
  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + author.hashCode();
    result = 31 * result + title.hashCode();
    return result;
  }
*/

  @Override
  public String toString() {
    return "Book{" +
      "author='" + author + '\'' +
      ", title='" + title + '\'' +
      ", year='" + year + '\'' +
      '}';
  }
}
