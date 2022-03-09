package frontcontroller.getcommands.admin;

import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddWorkerGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddWorkerGetCommand.class);
    private String addPage;
    public AddWorkerGetCommand(){
        MappingProperties properties = MappingProperties.getInstance();
        addPage = properties.getProperty("workeraddPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Adding worker GET command");
        return addPage;
    }
}
