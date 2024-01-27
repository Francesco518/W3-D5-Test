package it.epicode.be.dao;

import it.epicode.be.entities.Loan;

import javax.persistence.*;
import java.util.List;

public class LoanDAO {
    private EntityManagerFactory emf;

    public LoanDAO() {
        this.emf = Persistence.createEntityManagerFactory("Test W3-D5");
    }
    public void createLoan(Loan loan) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(loan);
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
    public Loan getLoanById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Loan.class, id);
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
    public List<Loan> getAllLoans() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT 1 FROM Loan 1");
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
    }
    public List<Loan> getLoansByUserCardNumber(String cardNumber) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber");
            query.setParameter("cardNumber", cardNumber);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
    public void removeLoan(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Loan loan = em.find(Loan.class, id);
            if (loan != null) {
                em.remove(loan);
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
