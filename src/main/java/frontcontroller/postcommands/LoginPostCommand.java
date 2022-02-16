package frontcontroller.postcommands;

import dao.user.UserDaoImpl;
import entity.user.User;
import frontcontroller.ServletCommand;
import service.UserService;
import util.MappingProperties;
import util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPostCommand implements ServletCommand {
    private UserService userService;
    private static String loginPage;
    private static String homepage;
    private static String errorpage;
    private static String userpage;
    private static String workerpage;

    public LoginPostCommand() {
        userService = new UserService(UserDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        loginPage = properties.getProperty("loginPagePost");
        homepage = properties.getProperty("homePagePost");
        errorpage = properties.getProperty("errorPagePost");
        userpage=properties.getProperty("userPagePost");
        workerpage = properties.getProperty("workerPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String resultPage = errorpage;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null && login.length() > 0 && password.length() > 0) {
            User user = userService.getUserByCredentials(login, password);
            if (user != null) {
                UserUtil.putToSession(req,user);
                switch (user.getRole_id()) {
                    case 1:

                        break;
                    case 2:
                        resultPage = workerpage;
                        break;
                    case 3:
                        resultPage = userpage;
                        break;
                }
            }
        }
        return resultPage;
    }
}
