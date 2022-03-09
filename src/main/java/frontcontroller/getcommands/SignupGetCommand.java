package frontcontroller.getcommands;

import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(SignupGetCommand.class);
    private static String signupPage;

    public SignupGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();
        signupPage = properties.getProperty("signupPage");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing signup page GET command");
        return signupPage;
    }
}
