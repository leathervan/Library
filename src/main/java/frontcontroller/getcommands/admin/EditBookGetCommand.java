package frontcontroller.getcommands.admin;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBookGetCommand implements ServletCommand {
    private BookService bookService;
    private String editPage;
    public EditBookGetCommand(){
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        editPage = properties.getProperty("bookeditPage");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String bookID = req.getParameter("bookID");
        Book book = bookService.getBook(Integer.valueOf(bookID));
        req.setAttribute("book",book);
        return editPage;
    }
}
