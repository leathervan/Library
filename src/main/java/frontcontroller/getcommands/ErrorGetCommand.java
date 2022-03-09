package frontcontroller.getcommands;

import frontcontroller.ServletCommand;
import frontcontroller.getcommands.admin.WorkersGetCommand;
import org.apache.log4j.Logger;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorGetCommand implements ServletCommand {
    private static final Logger log = Logger.getLogger(ErrorGetCommand.class);
    private static String errorPage;

    public ErrorGetCommand() {
        MappingProperties properties = MappingProperties.getInstance();
        errorPage = properties.getProperty("errorPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing error page GET command");
        return errorPage;
    }
}
