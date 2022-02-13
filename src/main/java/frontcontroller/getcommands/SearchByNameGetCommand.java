package frontcontroller.getcommands;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchByNameGetCommand implements ServletCommand {
    private BookService bookService;
    private static String userPage;

    public SearchByNameGetCommand() {
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        userPage = properties.getProperty("userPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Book> books=bookService.sortByName();
        req.setAttribute("books",books);
        return userPage;
    }
}
