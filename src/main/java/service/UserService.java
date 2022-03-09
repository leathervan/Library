package service;

import dao.user.UserDao;
import entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService {

    private static final Logger log = Logger.getLogger(UserService.class);
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User signUp(User user) {
        log.info("Creating a user");
        return userDao.create(user);
    }
    public void delete(User user) {
        log.info("Deleting a user");
        userDao.delete(user);
    }

    public User getUserByCredentials(String login, String password) {
        log.info("Getting user by login and password");
        return userDao.getUserByLoginAndPassword(login, password);
    }

    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userDao.getAll();
    }

    public User getUserById(long userId) {
        log.info("Getting a user by id");
        return userDao.get(userId);
    }

    public void changeUserStatus(long userId, boolean status) {
        log.info("Changing user's blocked status");
        userDao.changeStatus(userId, status);
    }

    public boolean checkLogin(String login){
        log.info("Checking a user by login");
        return userDao.checkLogin(login)!=null;
    }

    public List<User> getAllWorkers() {
        log.info("Getting all workers");
        return userDao.getAllWorkers();
    }

    public List<User> getAllCustomers() {
        log.info("Getting all customers");
        return userDao.getAllCustomers();
    }
}
