package frontcontroller.postcommands;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookPostCommand implements ServletCommand {
    private BookService bookService;
    private String adminPage;
    private String errorPage;
    public AddBookPostCommand(){
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminPagePost");
        errorPage = properties.getProperty("errorPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String resultPage = adminPage;
        Book newbook = new Book();
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
        bookService.createBook(newbook);
        return resultPage;
    }
}
