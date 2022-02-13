package dao.book;

import dao.DAO;
import entity.Book;

import java.util.List;

public interface BookDao extends DAO<Book> {
    List<Book> getBooksByName(String name);
    List<Book> getBooksByAuthor(String author);
    List<Book> sortByName();
    List<Book> sortByAuthor();
    List<Book> sortByEdition();
    List<Book> sortByYearEdition();
}
