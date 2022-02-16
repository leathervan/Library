package service;

import dao.book.BookDao;
import entity.Book;

import java.util.List;

public class BookService {
    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book createBook(Book book){
        return bookDao.create(book);
    }

    public boolean deleteBook(Book book){
        int size=bookDao.getAll().size();
        bookDao.delete(book);
        return bookDao.get(book.getId())==null && bookDao.getAll().size()!=size;
    }

    public boolean editBook(Book oldBook,Book newBook){
        bookDao.update(oldBook,newBook);
        return bookDao.get(oldBook.getId()).equals(newBook);
    }

    public Book getBook(long id){
        return bookDao.get(id);
    }

    public List<Book> getAllBook(){
        return bookDao.getAll();
    }

    public List<Book> getBooksByName(String name){
        return bookDao.getBooksByName(name).size()>0?bookDao.getBooksByName(name):null;
    }

    public List<Book> getBooksByAuthor(String name){
        return bookDao.getBooksByAuthor(name).size()>0?bookDao.getBooksByAuthor(name):null;
    }

    public List<Book> sortByName(){
        return bookDao.sortByName();
    }

    public List<Book> sortByAuthor(){
        return bookDao.sortByAuthor();
    }

    public List<Book> sortByEdition(){
        return bookDao.sortByEdition();
    }

    public List<Book> sortByYearEdition(){
        return bookDao.sortByYearEdition();
    }
    public void increaseBookAmount(Book book){
        bookDao.increaseBookAmount(book);
    }
    public void decreaseBookAmount(Book book){
        bookDao.decreaseBookAmount(book);
    }
}
