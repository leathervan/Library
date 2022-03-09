package service;

import dao.book.BookDao;
import entity.Book;
import org.apache.log4j.Logger;

import java.util.List;

public class BookService {

    private static final Logger log = Logger.getLogger(BookService.class);
    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book createBook(Book book){
        log.info("Creating a book");
        return bookDao.create(book);
    }

    public boolean deleteBook(Book book){
        log.info("Deleting a book");
        int size=bookDao.getAll().size();
        bookDao.delete(book);
        return bookDao.get(book.getId())==null && bookDao.getAll().size()!=size;
    }

    public boolean editBook(Book oldBook,Book newBook){
        log.info("Editing a book");
        bookDao.update(oldBook,newBook);
        return bookDao.get(oldBook.getId()).equals(newBook);
    }

    public Book getBook(long id){
        log.info("Getting a book by id");
        return bookDao.get(id);
    }

    public List<Book> getAllBook(){
        log.info("Getting all books");
        return bookDao.getAll();
    }

    public List<Book> getBooksByName(String name){
        log.info("Getting all books by name");
        return bookDao.getBooksByName(name).size()>0?bookDao.getBooksByName(name):null;
    }

    public List<Book> getBooksByAuthor(String name){
        log.info("Getting all books by author");
        return bookDao.getBooksByAuthor(name).size()>0?bookDao.getBooksByAuthor(name):null;
    }

    public List<Book> sortByName(){
        log.info("Sorting books by name");
        return bookDao.sortByName();
    }

    public List<Book> sortByAuthor(){
        log.info("Sorting books by author");
        return bookDao.sortByAuthor();
    }

    public List<Book> sortByEdition(){
        log.info("Sorting books by edition");
        return bookDao.sortByEdition();
    }

    public List<Book> sortByYearEdition(){
        log.info("Sorting books by year edition");
        return bookDao.sortByYearEdition();
    }

    public void increaseBookAmount(Book book){
        log.info("Increasing book's amount");
        bookDao.increaseBookAmount(book);
    }

    public void decreaseBookAmount(Book book){
        log.info("Decreasing book's amount");
        bookDao.decreaseBookAmount(book);
    }
}
