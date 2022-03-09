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

public class WorkersGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(WorkersGetCommand.class);
    private UserService userService;
    private String workersPage;
    public WorkersGetCommand(){
        userService = new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        workersPage = properties.getProperty("adminWorkers");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing admin workers page GET command");
        List<User> workers = userService.getAllWorkers();
        req.setAttribute("workers",workers);
        return workersPage;
    }
}
