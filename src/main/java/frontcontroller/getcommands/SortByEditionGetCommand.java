package frontcontroller.getcommands;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class SortByEditionGetCommand implements ServletCommand {
    private BookService bookService;
    private static String userPage;
    private static final Integer countPage = 3;

    public SortByEditionGetCommand() {
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        userPage = properties.getProperty("userPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Book> books=bookService.sortByEdition();
        req.setAttribute("countPage",(books.size()/countPage));//pagination view
        req.setAttribute("books",books.stream().limit(countPage).collect(Collectors.toList()));//start
        if(req.getParameter("page") != null){
            int number = Integer.valueOf(req.getParameter("number"));
            books=bookService.getAllBook().stream().skip((number-1)*countPage).limit(countPage).collect(Collectors.toList());
            req.setAttribute("books",books);
        }
        return userPage;
    }
}
