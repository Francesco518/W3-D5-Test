package it.epicode.be.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    private CatalogItem borrowedItem;

    @Temporal(TemporalType.DATE)
    private Date loanStartDate;

    @Temporal(TemporalType.DATE)
    private Date expectedRepaymentDate;

    @Temporal(TemporalType.DATE)
    private Date effectiveReturnDate;

    public Loan() {

    }
    public Loan(User user, CatalogItem borrowedItem) {
        this.user = user;
        this.borrowedItem = borrowedItem;
        this.loanStartDate = new Date();
        this.expectedRepaymentDate = calculateExpectedRepaymentDate();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CatalogItem getBorrowedItem() {
        return borrowedItem;
    }

    public void setBorrowedItem(CatalogItem borrowedItem) {
        this.borrowedItem = borrowedItem;
    }

    public Date getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public Date getExpectedRepaymentDate() {
        return expectedRepaymentDate;
    }

    public void setExpectedRepaymentDate(Date expectedRepaymentDate) {
        this.expectedRepaymentDate = expectedRepaymentDate;
    }

    public Date getEffectiveReturnDate() {
        return effectiveReturnDate;
    }

    public void setEffectiveReturnDate(Date effectiveReturnDate) {
        this.effectiveReturnDate = effectiveReturnDate;
    }
    private Date calculateExpectedRepaymentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loanStartDate);
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        return calendar.getTime();
    }
}
