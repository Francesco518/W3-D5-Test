package it.epicode.be.dao;

import it.epicode.be.entities.CatalogItem;

import javax.persistence.*;
import java.util.List;

public class CatalogItemsDAO {
    private EntityManagerFactory emf;

    public CatalogItemsDAO() {
        this.emf = Persistence.createEntityManagerFactory("Test W3-D5");
    }
    public void addCatalogItem(CatalogItem catalogItem) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(catalogItem);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
    public CatalogItem getCatalogItemById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(CatalogItem.class, id);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
    public List<CatalogItem> getAllCatalogItems() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM CatalogItem c");
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
    public void removeCatalogItem(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            CatalogItem catalogItem = em.find(CatalogItem.class, id);
            if (catalogItem != null) {
                em.remove(catalogItem);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
}
