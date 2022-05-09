package Entities;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CountriesRepository extends GenericRepository<CountriesEntity>{
    EntityManager em;
    public CountriesRepository(EntityManager em){
        this.em = em;
    }
    public boolean create(CountriesEntity c){
        if (c.getId() == null) {
            em.persist(c);
        } else {
            c = em.merge(c);
        }
        return true;
    }
    public CountriesEntity findById(int id){
        TypedQuery<CountriesEntity> q = em.createQuery("SELECT c FROM CountriesEntity c WHERE c.id = :id", CountriesEntity.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    public CountriesEntity findByName(String name){
        List list = em.createNamedQuery("findCountryByName")
                .setParameter("name", name + "%")
                .getResultList();

        return (CountriesEntity) list.get(0);
    }

}
