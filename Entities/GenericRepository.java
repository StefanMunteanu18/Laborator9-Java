package Entities;

import org.eclipse.persistence.jpa.rs.resources.common.AbstractEntityResource;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;

public abstract class GenericRepository
        <T extends AbstractEntity> {
    private EntityManager em;

    public void setEm(EntityManager em){
        this.em = em;
    }
    public boolean persist(T entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        return true;
    }
}