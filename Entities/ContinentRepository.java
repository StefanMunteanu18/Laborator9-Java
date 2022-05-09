package Entities;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

public class ContinentRepository extends GenericRepository<ContinentsEntity>{
    EntityManager em;
    public ContinentRepository(EntityManager em){
        this.em = em;
    }
    public boolean create(ContinentsEntity c){
        if (c.getId() == null) {
            em.persist(c);
        } else {
            c = em.merge(c);
        }
        return true;
    }
    public ContinentsEntity findById(int id){
        TypedQuery<ContinentsEntity> q = em.createQuery("SELECT c FROM ContinentsEntity c WHERE c.id = :id", ContinentsEntity.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }


    public ContinentsEntity findByName(String name){
        return (ContinentsEntity) em.createNamedQuery("findContinentByName")
                .setParameter("name", name + "%")
                .getResultList();
    }

}
