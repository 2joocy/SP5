/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBMapper;

import entity.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author William Pfaffe
 */
public class DBMapper {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private EntityManager em = emf.createEntityManager();

    public List<Country> getAllCountries() {
        Query query = em.createQuery("SELECT c from Country c");
        List<Country> list = query.getResultList();
        return list;
    }

    public List<Country> getCountryGreaterThanPopulation(int population) {
        Query query = em.createQuery("SELECT c from Country c where c.population > :popu");
        query.setParameter("popu", population);
        List<Country> list = query.getResultList();
        return list;
    }

    public List<Country> getCountryByCountryCode(String cc) {
        Query query = em.createQuery("SELECT c from Country c where c.code = :cc");
        query.setParameter("cc", cc);
        List<Country> list = query.getResultList();
        return list;
    }

    public Country getDenmark() {
        Query query = em.createQuery("SELECT c from Country c where c.code = :cc");
        query.setParameter("cc", "DNK");
        Country list = (Country) query.getSingleResult();
        return list;
    }

}
