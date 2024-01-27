package it.epicode.be.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Book extends CatalogItem{
    private String author;
    private String type;
    public Book() {
        super();
    }
    public Book(String isbn, String title, int yearOfPublication, int numberOfPages, String author, String type) {
        super(isbn, title, yearOfPublication, numberOfPages);
        this.author = author;
        this.type = type;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
