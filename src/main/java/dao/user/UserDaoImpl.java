package dao.user;

import connection.MyConnectionPool;
import dao.QUERY;
import entity.user.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class);
    private MyConnectionPool connectionPool;

    private UserDaoImpl() {
        connectionPool = MyConnectionPool.getInstance();
    }

    private static UserDaoImpl instance;
    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public User get(long id) {
        User user=null;
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_USER.query());
            pstmt.setString(1,String.valueOf(id));
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getInt(7));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs=stmt.executeQuery(QUERY.GET_ALL_USERS.query());
            while (rs.next()){
                users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getInt(7)));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public User create(User user) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.CREATE_USER.query(),Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,user.getEmail());
            pstmt.setString(2,user.getPassword());
            pstmt.setString(3,user.getName());
            pstmt.setString(4,user.getSurname());
            pstmt.setString(5,String.valueOf(user.getRoleId()));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                log.error("User creation is failed");
            } else {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) user.setId(generatedKeys.getInt(1));
                    else log.error("Failed to create user, no obtained id");
                }
            }
        }catch (SQLException e){
            user.setId(-1);
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void update(User oldUser, User newUser) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.EDIT_USER.query());
            pstmt.setString(1,newUser.getEmail());
            pstmt.setString(2,newUser.getPassword());
            pstmt.setString(3,newUser.getName());
            pstmt.setString(4,newUser.getSurname());
            pstmt.setString(5,String.valueOf(newUser.getRoleId()));
            pstmt.setString(6,String.valueOf(oldUser.getId()));
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(User user) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(QUERY.DELETE_USER.query());
            stmt.setString(1,String.valueOf(user.getId()));
            stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user=null;
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_USER_BY_LOGIN_AND_PASSWORD.query());
            pstmt.setString(1,login);
            pstmt.setString(2,password);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getInt(7));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public User checkLogin(String login) {
        User user=null;
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_USER_BY_LOGIN.query());
            pstmt.setString(1,login);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getInt(7));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public void changeStatus(long userId, boolean status) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.BLOCK_USER.query());
            if(status) pstmt = connection.prepareStatement(QUERY.UNBLOCK_USER.query());
            pstmt.setString(1,String.valueOf(userId));
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<User> getAllCustomers() {
        List<User> users=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs=stmt.executeQuery(QUERY.GET_ALL_CUSTOMERS.query());
            while (rs.next()){
                users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getInt(7)));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return users;
    }

    @Override
    public List<User> getAllWorkers() {
        List<User> users=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs=stmt.executeQuery(QUERY.GET_ALL_WORKERS.query());
            while (rs.next()){
                users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getInt(7)));
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return users;
    }
}
