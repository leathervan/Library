package frontcontroller.getcommands.admin;

import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(AddBookGetCommand.class);
    private String addPage;
    public AddBookGetCommand(){
        MappingProperties properties = MappingProperties.getInstance();
        addPage = properties.getProperty("bookaddPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Adding book GET command");
        return addPage;
    }
}
