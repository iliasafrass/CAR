package car.tp4.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Panier implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	private String idClient;
	private List<Book> books;
	
	public Panier() {}
	
	
	
	public Panier(String idClient, List<Book> books) {
		super();
		this.idClient = idClient;
		this.books = books;
	}


	

	public String getId_client() {
		return idClient;
	}
	public void setId_client(String id_client) {
		this.idClient = id_client;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
	
}
