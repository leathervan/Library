package frontcontroller.postcommands;

import dao.book.BookDaoImpl;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.user.User;
import entity.user.UserRole;
import frontcontroller.ServletCommand;
import service.BookService;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddWorkerPostCommand implements ServletCommand {
    private UserService userService;
    private String adminPage;
    private String errorPage;
    public AddWorkerPostCommand(){
        userService=new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        adminPage = properties.getProperty("adminWorkersPagePost");
        errorPage = properties.getProperty("errorPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String resultPage = adminPage;
        Book newbook = new Book();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        User user=new User();
        user.setRole_id(UserRole.WORKER.ordinal());
        if(email != null && email.length()>0) user.setEmail(email);
        if(password != null && password.length()>0) user.setPassword(password);
        if(name != null && name.length()>0) user.setName(name);
        if(surname != null && surname.length()>0) user.setSurname(surname);
        userService.signUp(user);
        return resultPage;
    }
}
