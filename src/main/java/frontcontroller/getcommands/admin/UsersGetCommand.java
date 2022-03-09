package frontcontroller.getcommands.admin;

import dao.user.UserDaoImpl;
import entity.user.User;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AdminPageGetCommand.class);
    private UserService userService;
    private String usersPage;
    public UsersGetCommand(){
        userService = new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        usersPage = properties.getProperty("adminUsers");

    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing admin users page GET command");
        List<User> users = userService.getAllCustomers();
        req.setAttribute("users",users);
        return usersPage;
    }
}
