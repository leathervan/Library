package frontcontroller.getcommands.admin;

import dao.user.UserDaoImpl;
import entity.user.User;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUnblockUserGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(BlockUnblockUserGetCommand.class);
    private UserService userService;
    private String blockedPage;
    public BlockUnblockUserGetCommand(){
        userService = new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        blockedPage = properties.getProperty("userBlockedPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Blocking or unblocking user GET command");
        String userID = req.getParameter("userID");
        User user = userService.getUserById(Integer.valueOf(userID));
        userService.changeUserStatus(user.getId(),user.getBlocked());
        return blockedPage;
    }
}
