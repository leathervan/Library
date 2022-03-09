package frontcontroller.postcommands;

import dao.user.UserDaoImpl;
import entity.user.User;
import entity.user.UserRole;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.UserService;
import util.EncryptionUtil;
import util.MappingProperties;
import util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupPostCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(SignupPostCommand.class);
    private UserService userService;
    private static String signupPage;
    private static String homepage;
    private static String errorpage;
    private static String userpage;

    public SignupPostCommand() {
        userService=new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        signupPage = properties.getProperty("signupPagePost");
        homepage = properties.getProperty("homePagePost");
        errorpage = properties.getProperty("errorPagePost");
        userpage=properties.getProperty("userPagePost");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing signup page POST command");
        String resultPage=errorpage;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        if (login != null && password != null && name!=null && surname!=null) {
            if(login.length() > 0 && password.length() > 0 && name.length()>0 && surname.length()>0){
                if(userService.checkLogin(login)) resultPage=errorpage;
                else {
                    User user=new User(1,login, EncryptionUtil.md5(password),name,surname, UserRole.USER.ordinal());
                    userService.signUp(user);
                    UserUtil.putToSession(req,user);
                    resultPage = userpage;
                }
            }
        }
        return resultPage;
    }
}
