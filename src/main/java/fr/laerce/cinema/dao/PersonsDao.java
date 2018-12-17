package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Component
public class PersonsDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(Persons p){
        entityManager.persist(p);
    }

    public List<Persons> getAll(){
        Query query = entityManager.createQuery("Select p from Persons p");
        return query.getResultList();
    }

    public Persons getById(long id){
        Persons retVal = null;
        Query query = entityManager.createQuery("select p from Persons p where p.id = :id");
        query.setParameter("id", id);
        List<Persons> persons = query.getResultList();
        if(!persons.isEmpty()){
            retVal = persons.get(0);
        }
        return retVal;
    }
    public Persons getByName(String name){
        Persons lol = null;
        Query query = entityManager.createQuery("select p from Persons p where p.surname = :lol");
        query.setParameter("lol", lol);
        List<Persons> persons = query.getResultList();
        if(!persons.isEmpty()){
            lol = persons.get(0);
        }
        return lol;
    }

    public void update(String surname,String givenname,Integer birthYear)
    {
        Persons pUpdate=this.getByName (surname);
        pUpdate.setGivenname (givenname);
        pUpdate.setSurname (surname);
        pUpdate.setBirthYear (birthYear);
    }
    @Transactional
    public void delete(long id)
    {
        Persons personne = entityManager.find (Persons.class, id);
        if (personne == null) {
            System.out.println("Personne non trouvée");
        } else {
            entityManager.remove(personne);
        }
    }
}
