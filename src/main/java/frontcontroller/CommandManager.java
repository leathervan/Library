package frontcontroller;

import frontcontroller.getcommands.*;
import frontcontroller.getcommands.worker.WorkerPageGetCommand;
import frontcontroller.postcommands.LoginPostCommand;
import frontcontroller.postcommands.SignupPostCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
    private final Map<String, ServletCommand> getCommands;
    private final Map<String, ServletCommand> postCommands;
    public CommandManager() {
        getCommands = new ConcurrentHashMap<>();
        postCommands = new ConcurrentHashMap<>();

        getCommands.put("/login", new LoginGetCommand());
        getCommands.put("/signup", new SignupGetCommand());
        getCommands.put("/error", new ErrorGetCommand());
        getCommands.put("/user", new UserPageGetCommand());
        getCommands.put("/user_sort_by_name", new SortByNameGetCommand());
        getCommands.put("/user_sort_by_author", new SortByAuthorGetCommand());
        getCommands.put("/user_sort_by_edition", new SortByEditionGetCommand());
        getCommands.put("/user_sort_by_year_edition", new SortByYearEditionGetCommand());
        getCommands.put("/orderbook", new OrderGetCommand());
        getCommands.put("/userProfile",new UserProfileGetCommand());
        getCommands.put("/returnbook",new ReturnBookGetCommand());
        getCommands.put("/rejectorder", new RejectOrderGetCommand());
        getCommands.put("/worker",new WorkerPageGetCommand());

        postCommands.put("/signup", new SignupPostCommand());
        postCommands.put("/login", new LoginPostCommand());
    }

    public ServletCommand getGetCommand(HttpServletRequest req) {
        String command = getMapping(req);
        if (getCommands.get(command) == null) {
            return getCommands.get("/");
        }
        return getCommands.get(command);
    }

    public ServletCommand getPostCommand(HttpServletRequest req) {
        String command = getMapping(req);
        if (postCommands.get(command) == null) {
            return postCommands.get("/");
        }
        return postCommands.get(command);
    }

    private String getMapping(HttpServletRequest req) {
        String mapping = req.getRequestURI().substring(req.getContextPath().length());
        if (mapping.endsWith("/")) {
            mapping = mapping.substring(0, mapping.length() - 1);
        }
        return mapping;
    }
}
