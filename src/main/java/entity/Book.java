package entity;

import java.util.Objects;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private String edition;
    private Integer year_edition;
    private Integer amount;

    public Book(Integer id, String name, String author, String edition, Integer year_edition, Integer amount) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.year_edition = year_edition;
        this.amount = amount;
    }
    public Book(String name, String author, String edition, Integer year_edition, Integer amount) {
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.year_edition = year_edition;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) && name.equals(book.name) && author.equals(book.author) && edition.equals(book.edition) && year_edition.equals(book.year_edition) && amount.equals(book.amount);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", year_edition=" + year_edition +
                ", amount=" + amount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getYear_edition() {
        return year_edition;
    }

    public void setYear_edition(Integer year_edition) {
        this.year_edition = year_edition;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
