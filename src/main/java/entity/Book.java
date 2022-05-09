package entity;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private String edition;
    private Integer yearEdition;
    private Integer amount;

    public Book(Integer id, String name, String author, String edition, Integer yearEdition, Integer amount) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.yearEdition = yearEdition;
        this.amount = amount;
    }
    public Book(String name, String author, String edition, Integer yearEdition, Integer amount) {
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.yearEdition = yearEdition;
        this.amount = amount;
    }
    public Book() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) && name.equals(book.name) && author.equals(book.author) && edition.equals(book.edition) && yearEdition.equals(book.yearEdition) && amount.equals(book.amount);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", year_edition=" + yearEdition +
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

    public Integer getYearEdition() {
        return yearEdition;
    }

    public void setYearEdition(Integer yearEdition) {
        this.yearEdition = yearEdition;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
