package frontcontroller.postcommands;

import dao.book.BookDaoImpl;
import entity.Book;
import frontcontroller.ServletCommand;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserPagePostCommand implements ServletCommand {
    private BookService bookService;
    private static String userPage;

    public UserPagePostCommand() {
        bookService = new BookService(BookDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        userPage=properties.getProperty("userPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
/*        List<Book> books=new ArrayList<>();
        String search=req.getParameter("search");
        System.out.println(search);
        if(search.length()>0){
            books = bookService.getBooksByName(search);
            if(books==null){
                books = bookService.getBooksByAuthor(search);
                if(books!=null) req.setAttribute("books",books);
            }
            else req.setAttribute("books",books);
        }*/
        return userPage;
    }
}
