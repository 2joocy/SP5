/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBMapper;

import IF.PersonIF;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author William Pfaffe
 */
public class DBMapper implements PersonIF{
    private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("pul");
    private final EntityManager entitymanager = emfactory.createEntityManager();
    
    @Override
    public void addEntityManager(EntityManagerFactory emf) {
        this.emfactory = emf;
    }

    @Override
    public void addPerson(Person p) {
        entitymanager.getTransaction().begin();
        entitymanager.persist(p);
        entitymanager.getTransaction().commit();
    }

    @Override
    public void deletePerson(int id) {
        Query query = entitymanager.createNamedQuery("Person.deleteById");
        query.setParameter("id", id);
    }

    @Override
    public Person getPerson(int id) {
        Query query = entitymanager.createNamedQuery("Person.findById");
        query.setParameter("id", id);
        List<Person> list = (List<Person>) query.getResultList();
        return list.get(0);
    }

    @Override
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList();
        Query query = entitymanager.createNamedQuery("Person.getPersons");
        List<Person> list = (List<Person>) query.getResultList();
        return list;
    }

    @Override
    public Person editPerson(Person p) {
        this.deletePerson(p.getId());
        this.addPerson(p);
        return p;
    }
    
}
