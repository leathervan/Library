package frontcontroller.getcommands.admin;

import dao.book.BookDaoImpl;
import frontcontroller.ServletCommand;
import service.BookService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddWorkerGetCommand implements ServletCommand {
    private String addPage;
    public AddWorkerGetCommand(){
        MappingProperties properties = MappingProperties.getInstance();
        addPage = properties.getProperty("workeraddPage");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return addPage;
    }
}
