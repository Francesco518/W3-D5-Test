package it.epicode.be.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table
public class Magazine extends CatalogItem{
    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    public Magazine() {
        super();
    }
    public Magazine(String isbn, String title, int yearOfPublication, int numberOfPages, Frequency frequency) {
        super(isbn, title, yearOfPublication, numberOfPages);
        this.frequency = frequency;
    }
    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }
}
