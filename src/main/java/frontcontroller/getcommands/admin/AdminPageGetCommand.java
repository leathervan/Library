package frontcontroller.getcommands.admin;

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

public class AdminPageGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AdminPageGetCommand.class);
    private BookService bookService;
    private static String adminPage;
    private static final Integer countPage = 10;
    private String sort;

    public AdminPageGetCommand() {
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing admin start page GET command");
        List<Book> books=bookService.getAllBook();

        pagination(req,books);
        searchBooks(req,books);
        return adminPage;
    }

    private void searchBooks(HttpServletRequest req,List<Book> books){
        log.info("Searching books on admin page GET command");
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
        if(books.size()%countPage==0) req.setAttribute("countPage",(books.size()/countPage));
        else req.setAttribute("countPage",(books.size()/countPage) + 1);//pagination view
        req.setAttribute("books",books.stream().limit(countPage).collect(Collectors.toList()));//start

        if(req.getParameter("page") != null){
            int number = Integer.valueOf(req.getParameter("page"));
            books = books.stream().skip((number-1)*countPage).limit(countPage).collect(Collectors.toList());
            req.setAttribute("books",books);
        }
    }
}
