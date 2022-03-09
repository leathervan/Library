package frontcontroller.postcommands;

import dao.user.UserDaoImpl;
import entity.user.User;
import entity.user.UserRole;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.UserService;
import util.EncryptionUtil;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddWorkerPostCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddWorkerPostCommand.class);
    private UserService userService;
    private String adminPage;
    private String errorPage;
    public AddWorkerPostCommand(){
        userService=new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminWorkersPagePost");
        errorPage = properties.getProperty("errorPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Adding worker POST command");
        String resultPage = adminPage;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        User user=new User();
        user.setRole_id(UserRole.WORKER.ordinal());
        if(email != null && email.length()>0) user.setEmail(email);
        if(password != null && password.length()>0) user.setPassword(EncryptionUtil.md5(password));
        if(name != null && name.length()>0) user.setName(name);
        if(surname != null && surname.length()>0) user.setSurname(surname);
        userService.signUp(user);
        return resultPage;
    }
}
