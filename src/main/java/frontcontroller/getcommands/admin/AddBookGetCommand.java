package frontcontroller.getcommands.admin;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookGetCommand implements ServletCommand {
    private BookService bookService;
    private String addPage;
    public AddBookGetCommand(){
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        addPage = properties.getProperty("bookaddPage");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return addPage;
    }
}
