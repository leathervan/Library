package frontcontroller.getcommands;

import frontcontroller.ServletCommand;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginGetCommand implements ServletCommand {

    private static String loginPage;

    public LoginGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();
        loginPage = properties.getProperty("loginPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return loginPage;
    }
}
