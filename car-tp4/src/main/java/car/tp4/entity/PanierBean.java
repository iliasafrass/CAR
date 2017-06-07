package car.tp4.entity;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class PanierBean{

    @PersistenceContext
    private EntityManager em;

    public void init() {
        clear();
    }

    public void add(Panier command) {
        em.persist(command);
    }

    public void clear() {
        Query q = em.createQuery("DELETE FROM Panier");
        q.executeUpdate();
        em.flush();
        em.clear();
    }

    public void initCommands() {
        clear();
    }

    public List<Panier> getListCommands() {
        Query q = em.createQuery("SELECT c FROM Panier c");
        List<Panier> commands = q.getResultList();
        return commands;
    }

    public List<String> getList() {
        Query q = em.createQuery("SELECT DISTINCT c.customer FROM Panier c");
        List<String> customers = q.getResultList();
        return customers;
    }

    public void addListBook(LinkedList lb, String s){
        Query q = em.createQuery("UPDATE Panier SET c.books WHERE c.id = "+s);
        List<String> customers = q.getResultList();
    }
}