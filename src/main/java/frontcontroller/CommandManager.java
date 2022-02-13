package frontcontroller;

import frontcontroller.getcommands.*;
import frontcontroller.postcommands.LoginPostCommand;
import frontcontroller.postcommands.SignupPostCommand;
import frontcontroller.postcommands.UserPagePostCommand;

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

        postCommands.put("/signup", new SignupPostCommand());
        postCommands.put("/login", new LoginPostCommand());
        postCommands.put("/user", new UserPagePostCommand());
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