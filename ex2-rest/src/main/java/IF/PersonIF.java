/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IF;

import entities.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author William Pfaffe
 */
public interface PersonIF {
    public void addEntityManager(EntityManagerFactory emf);
    public void addPerson(Person p);
    public void deletePerson(int id);
    public Person getPerson(int id);
    public List<Person> getPersons();
    public Person editPerson(Person p);
}
