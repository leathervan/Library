package dao.book;

import connection.MyConnectionPool;
import dao.QUERY;
import entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao{
    private MyConnectionPool connectionPool;

    private BookDaoImpl() {
        connectionPool = MyConnectionPool.getInstance();
    }

    private static BookDaoImpl instance;
    public static BookDaoImpl getInstance() {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }

    @Override
    public Book get(long id) {
        Book book=null;
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_BOOK.query());
            pstmt.setString(1,String.valueOf(id));
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs=stmt.executeQuery(QUERY.GET_ALL_BOOKS.query());
            while (rs.next()){
                books.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return books;
    }

    @Override
    public Book create(Book book) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.CREATE_BOOK.query(),Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,book.getName());
            pstmt.setString(2,book.getAuthor());
            pstmt.setString(3,book.getEdition());
            pstmt.setString(4,String.valueOf(book.getYear_edition()));
            pstmt.setString(5,String.valueOf(book.getAmount()));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Receipt creation is failed");
            } else {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        book.setId(generatedKeys.getInt(1));
                    } else {
                        System.out.println("Failed to create user, no obtained id");
                    }
                }
            }
        }catch (SQLException e){
            book.setId(-1);
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return book;
    }

    @Override
    public void update(Book oldBook, Book newBook) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.EDIT_BOOK.query());
            pstmt.setString(1,newBook.getName());
            pstmt.setString(2,newBook.getAuthor());
            pstmt.setString(3,newBook.getEdition());
            pstmt.setString(4,String.valueOf(newBook.getYear_edition()));
            pstmt.setString(5,String.valueOf(newBook.getAmount()));
            pstmt.setString(6,String.valueOf(oldBook.getId()));
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Book book) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(QUERY.DELETE_BOOK.query());
            stmt.setString(1,String.valueOf(book.getId()));
            stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Book> getBooksByName(String name) {
        List<Book> books=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.SEARCH_BOOK_BY_NAME.query());
            pstmt.setString(1, name);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                books.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return books;
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        List<Book> books=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.SEARCH_BOOK_BY_AUTHOR.query());
            pstmt.setString(1, author);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                books.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return books;
    }

    @Override
    public List<Book> sortByName() {
        List<Book> books=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.SORT_BOOK_BY_NAME.query());
            while (rs.next()){
                books.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> sortByAuthor() {
        List<Book> books=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.SORT_BOOK_BY_AUTHOR.query());
            while (rs.next()){
                books.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> sortByEdition() {
        List<Book> books=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.SORT_BOOK_BY_EDITION.query());
            while (rs.next()){
                books.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> sortByYearEdition() {
        List<Book> books=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.SORT_BOOK_BY_YEAR_EDITION.query());
            while (rs.next()){
                books.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return books;
    }

    @Override
    public void increaseBookAmount(Book book) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.INCREASE_AMOUNT_OF_BOOK.query());
            pstmt.setString(1,String.valueOf(book.getId()));
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void decreaseBookAmount(Book book) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.DECREASE_AMOUNT_OF_BOOK.query());
            pstmt.setString(1,String.valueOf(book.getId()));
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
