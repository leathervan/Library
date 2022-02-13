package frontcontroller.getcommands;

import frontcontroller.ServletCommand;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupGetCommand implements ServletCommand {
    private static String signupPage;

    public SignupGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();
        signupPage = properties.getProperty("signupPage");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String resultPage = signupPage;
        return resultPage;
    }
}
