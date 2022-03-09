package frontcontroller.getcommands;

import frontcontroller.ServletCommand;
import frontcontroller.getcommands.admin.WorkersGetCommand;
import org.apache.log4j.Logger;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(LoginGetCommand.class);
    private static String loginPage;

    public LoginGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();
        loginPage = properties.getProperty("loginPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing login page GET command");
        return loginPage;
    }
}
