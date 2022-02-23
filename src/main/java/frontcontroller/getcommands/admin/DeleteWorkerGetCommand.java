package frontcontroller.getcommands.admin;

import dao.book.BookDaoImpl;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.user.User;
import frontcontroller.ServletCommand;
import service.BookService;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteWorkerGetCommand implements ServletCommand {
    private UserService userService;
    private static String workerDeletePage;
    private static String errorPage;

    public DeleteWorkerGetCommand(){
        userService = new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        workerDeletePage = properties.getProperty("workerDeletePage");
        errorPage = properties.getProperty("errorPage");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String resultPage = workerDeletePage;
        String userID = req.getParameter("userID");
        User user = userService.getUserById(Integer.valueOf(userID));
        if(user == null) resultPage = errorPage;
        else userService.delete(user);
        return resultPage;
    }
}
