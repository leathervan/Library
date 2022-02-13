package dao.user;

import dao.DAO;
import entity.user.User;

public interface UserDao extends DAO<User> {
    User getUserByLoginAndPassword(String login, String password);

    boolean changeStatus(long userId, boolean status);

    User checkLogin(String login);

}
