package com.amhfilho.google.photosuploader.persistence;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Default implementation of a TargetFileRepository, uses a JPA EntityManager
 */
public class DefaultTargetFileRepository implements TargetFileRepository {
    private EntityManager em;

    public DefaultTargetFileRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<TargetFile> findAll() {
        return em.createQuery("select t from TargetFile t", TargetFile.class).getResultList();
    }

    @Override
    public void save(TargetFile targetFile) {
        if(targetFile!=null) {
            if (targetFile.getId() == null){
                em.persist(targetFile);
            }
            else {
                em.merge(targetFile);
            }
        }
    }

    @Override
    public int deleteAll() {
        return em.createQuery("delete from TargetFile",TargetFile.class).executeUpdate();
    }


}
