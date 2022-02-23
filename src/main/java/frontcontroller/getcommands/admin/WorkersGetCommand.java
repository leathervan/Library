package frontcontroller.getcommands.admin;

import dao.book.BookDaoImpl;
import dao.user.UserDaoImpl;
import entity.user.User;
import frontcontroller.ServletCommand;
import service.BookService;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WorkersGetCommand implements ServletCommand {
    private UserService userService;
    private String workersPage;
    public WorkersGetCommand(){
        userService = new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        workersPage = properties.getProperty("adminWorkers");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> workers = userService.getAllWorkers();
        req.setAttribute("workers",workers);
        return workersPage;
    }
}
