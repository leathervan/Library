package frontcontroller.getcommands.user;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserPageGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(UserPageGetCommand.class);
    private BookService bookService;
    private static String userPage;
    private static final Integer countPage = 3;
    private String sort;

    public UserPageGetCommand() {
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        userPage = properties.getProperty("userPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing user page GET command");
        List<Book> books=bookService.getAllBook();

        pagination(req,books);
        searchBooks(req,books);
        return userPage;
    }

    private void searchBooks(HttpServletRequest req,List<Book> books){
        log.info("Searching book user GET command");
        String search = req.getParameter("search");
        if(search != null && search.length()>0){
            if(books!=null) {
                books = bookService.getBooksByName(search);
                if (books == null) {
                    books = bookService.getBooksByAuthor(search);
                    if (books != null) req.setAttribute("books", books);
                } else req.setAttribute("books", books);
                if(books==null) {
                    books=new ArrayList<>();
                    req.setAttribute("books", books);
                }
            }
            if(books.size()<=2) req.setAttribute("countPage",1);
            else req.setAttribute("countPage",(books.size()/countPage));
        }
    }
    private void pagination(HttpServletRequest req,List<Book> books){
        if(req.getParameter("sort") == null && sort!=null) books = checkSortParameter(books,sort);
        if(req.getParameter("sort") != null) {
            sort = req.getParameter("sort");
            books = checkSortParameter(books,sort);
        }

        req.setAttribute("countPage",(books.size()/countPage));//pagination view
        req.setAttribute("books",books.stream().limit(countPage).collect(Collectors.toList()));//start

        if(req.getParameter("page") != null){
            int number = Integer.valueOf(req.getParameter("page"));
            books = books.stream().skip((number-1)*countPage).limit(countPage).collect(Collectors.toList());
            req.setAttribute("books",books);
        }
    }
    private List<Book> checkSortParameter(List<Book> books, String sort){
        switch (sort) {
            case "name":
                books=bookService.sortByName();
                break;
            case "author":
                books=bookService.sortByAuthor();
                break;
            case "edition":
                books=bookService.sortByEdition();
                break;
            case "year":
                books=bookService.sortByYearEdition();
                break;
        }
        return books;
    }
}
