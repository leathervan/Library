package frontcontroller.getcommands.admin;

import dao.user.UserDaoImpl;
import entity.user.User;
import frontcontroller.ServletCommand;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUnblockUSerGetCommand implements ServletCommand {
    private UserService userService;
    private String blockedPage;
    public BlockUnblockUSerGetCommand(){
        userService = new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        blockedPage = properties.getProperty("userBlockedPage");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userID = req.getParameter("userID");
        User user = userService.getUserById(Integer.valueOf(userID));
        System.out.println(user);
        userService.changeUserStatus(user.getId(),user.getBlocked());
        System.out.println(userService.getUserById(Integer.valueOf(userID)));
        return blockedPage;
    }
}
