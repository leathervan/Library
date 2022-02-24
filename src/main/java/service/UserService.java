package service;

import dao.user.UserDao;
import entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User signUp(User user) {
        return userDao.create(user);
    }
    public void delete(User user) {
        userDao.delete(user);
    }

    public User getUserByCredentials(String login, String password) {;
        return userDao.getUserByLoginAndPassword(login, password);
    }

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public User getUserById(long userId) {
        return userDao.get(userId);
    }

    public void changeUserStatus(long userId, boolean status) {userDao.changeStatus(userId, status);}

    public boolean checkLogin(String login){
        return userDao.checkLogin(login)!=null;
    }

    public List<User> getAllWorkers() {
        return userDao.getAllWorkers();
    }

    public List<User> getAllCustomers() {
        return userDao.getAllCustomers();
    }
}
