package frontcontroller.postcommands;

import dao.user.UserDaoImpl;
import entity.user.User;
import entity.user.UserRole;
import frontcontroller.ServletCommand;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupPostCommand implements ServletCommand {
    private UserService userService;
    private static String signupPage;
    private static String homepage;
    private static String errorpage;

    public SignupPostCommand() {
        userService=new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        signupPage = properties.getProperty("signupPagePost");
        homepage = properties.getProperty("homePagePost");
        errorpage = properties.getProperty("errorPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String resultPage=errorpage;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        if (login != null && password != null && name!=null && surname!=null) {
            if(userService.checkLogin(login)) resultPage=errorpage;
            else {
                userService.signUp(new User(1,login,password,name,surname, UserRole.USER.ordinal()));
                resultPage = homepage;
            }
        }
        return resultPage;
    }
}
