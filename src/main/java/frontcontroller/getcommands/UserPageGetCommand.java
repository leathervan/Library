package frontcontroller.getcommands;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserPageGetCommand implements ServletCommand {
    private BookService bookService;
    private static String userPage;

    public UserPageGetCommand() {
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        userPage = properties.getProperty("userPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Book> books=bookService.getAllBook();
        req.setAttribute("books",books);
        String search = req.getParameter("search");
        if(search != null && search.length()>0){
            books = bookService.getBooksByName(search);
            if(books==null){
                books = bookService.getBooksByAuthor(search);
                if(books!=null) req.setAttribute("books",books);
            }
            else req.setAttribute("books",books);
        }
        return userPage;
    }
}
