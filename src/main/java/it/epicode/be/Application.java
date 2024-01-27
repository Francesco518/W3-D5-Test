package it.epicode.be;

import it.epicode.be.dao.CatalogItemsDAO;
import it.epicode.be.dao.LoanDAO;
import it.epicode.be.entities.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        CatalogItemsDAO catalogItemsDAO = new CatalogItemsDAO();
        LoanDAO loanDAO = new LoanDAO();

        Book book = new Book("123456789", "Harry Potter and the philosopher's stone", 1997, 300, "J.K. Rowling", "Fantasy");
        Magazine magazine = new Magazine("987654321", "Discover Peru", 1888, 30, Frequency.MONTHLY);

        catalogItemsDAO.addCatalogItem(book);
        catalogItemsDAO.addCatalogItem(magazine);

        User user = new User("Martin", "Gehrig",  parseDate("1995-09-06"), "123456789");

        Loan loan = new Loan(user, book);

        loanDAO.createLoan(loan);

        List<Loan> userLoans = loanDAO.getLoansByUserCardNumber(user.getCardNumber());
        System.out.println("Loans for user " + user.getFirstName() + " " + user.getLastName() + ":");
        for (Loan userLoan : userLoans) {
            System.out.println("Borrowed item: " + userLoan.getBorrowedItem().getTitle() +
                    ", Loan start date: " + userLoan.getLoanStartDate() +
                    ", Expected repayment date: " + userLoan.getExpectedRepaymentDate());
        }
        List<CatalogItem> allCatalogItems = catalogItemsDAO.getAllCatalogItems();
        System.out.println("\nAll Catalog Items:");
        for (CatalogItem item : allCatalogItems) {
            System.out.println(item.getTitle() + " (ISBN: " + item.getIsbn() + ")");
        }
        PersistenceConfig.getEntityManagerFactory().close();
    }
    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}