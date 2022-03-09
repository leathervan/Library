package frontcontroller.getcommands.admin;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(DeleteBookGetCommand.class);
    private BookService bookService;
    private static String bookDeletePage;
    private static String errorPage;

    public DeleteBookGetCommand(){
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        bookDeletePage = properties.getProperty("bookDeletePage");
        errorPage = properties.getProperty("errorPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Deleting book GET command");
        String resultPage = bookDeletePage;
        String bookID = req.getParameter("bookID");
        Book book = bookService.getBook(Integer.valueOf(bookID));
        if(book == null) resultPage = errorPage;
        else bookService.deleteBook(book);
        return resultPage;
    }
}
