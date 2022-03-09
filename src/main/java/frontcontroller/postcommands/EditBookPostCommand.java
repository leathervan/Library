package frontcontroller.postcommands;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBookPostCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(EditBookPostCommand.class);
    private BookService bookService;
    private String adminPage;
    private String errorPage;
    public EditBookPostCommand(){
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPagePost");
        errorPage = properties.getProperty("errorPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Editing book POST command");
        String resultPage = adminPage;
        Book oldbook = bookService.getBook(Integer.valueOf(req.getParameter("bookID")));
        Book newbook = bookService.getBook(Integer.valueOf(req.getParameter("bookID")));
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String edition = req.getParameter("edition");
        String year = req.getParameter("year");
        String amount = req.getParameter("amount");
        if(name != null && name.length()>0) newbook.setName(name);
        if(author != null && author.length()>0) newbook.setAuthor(author);
        if(edition != null && edition.length()>0) newbook.setEdition(edition);
        if(year != null && year.length()>0) newbook.setYear_edition(Integer.valueOf(year));
        if(amount != null && amount.length()>0 && Integer.valueOf(amount) >= 0) newbook.setAmount(Integer.valueOf(amount));
        bookService.editBook(oldbook,newbook);
        return resultPage;
    }
}
