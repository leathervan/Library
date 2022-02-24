package frontcontroller;

import frontcontroller.getcommands.*;
import frontcontroller.getcommands.admin.*;
import frontcontroller.getcommands.worker.ApproveOrderGetCommand;
import frontcontroller.getcommands.worker.SubsGetCommand;
import frontcontroller.getcommands.worker.WorkerPageGetCommand;
import frontcontroller.postcommands.*;

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
        getCommands.put("/orderbook", new OrderGetCommand());
        getCommands.put("/userProfile",new UserProfileGetCommand());
        getCommands.put("/returnbook",new ReturnBookGetCommand());
        getCommands.put("/rejectorder", new RejectOrderGetCommand());
        getCommands.put("/worker",new WorkerPageGetCommand());
        getCommands.put("/approve",new ApproveOrderGetCommand());
        getCommands.put("/subs",new SubsGetCommand());
        getCommands.put("/admin", new AdminPageGetCommand());
        getCommands.put("/deletebook", new DeleteBookGetCommand());
        getCommands.put("/editbook",new EditBookGetCommand());
        getCommands.put("/addbook",new AddBookGetCommand());
        getCommands.put("/adminWorkers",new WorkersGetCommand());
        getCommands.put("/adminCustomers", new UsersGetCommand());
        getCommands.put("/deleteworker",new DeleteWorkerGetCommand());
        getCommands.put("/addworker",new AddWorkerGetCommand());
        getCommands.put("/blockuser", new BlockUnblockUSerGetCommand());

        postCommands.put("/signup", new SignupPostCommand());
        postCommands.put("/login", new LoginPostCommand());
        postCommands.put("/approve", new ApproveOrderPostCommand());
        postCommands.put("/editbook",new EditBookPostCommand());
        postCommands.put("/addbook",new AddBookPostCommand());
        postCommands.put("/addworker",new AddWorkerPostCommand());
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
