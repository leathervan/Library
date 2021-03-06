package dao.subscription;

import connection.MyConnectionPool;
import dao.QUERY;
import entity.Subscription;
import entity.user.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    private static final Logger log = Logger.getLogger(SubscriptionDaoImpl.class);
    private MyConnectionPool connectionPool;

    private SubscriptionDaoImpl() {
        connectionPool = MyConnectionPool.getInstance();
    }

    private static SubscriptionDaoImpl instance;
    public static SubscriptionDaoImpl getInstance() {
        if (instance == null) {
            instance = new SubscriptionDaoImpl();
        }
        return instance;
    }
    @Override
    public Subscription get(long id) {
        Subscription subscription=null;
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_SUBSCRIPTION.query());
            pstmt.setString(1,String.valueOf(id));
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                subscription = new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subscription;
    }

    @Override
    public List<Subscription> getAll() {
        List<Subscription> subs=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.GET_ALL_SUBSCRIPTION.query());
            while (rs.next()){
                subs.add(new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subs;
    }

    @Override
    public Subscription create(Subscription subscription) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.CREATE_SUBSCRIPTION.query(),Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,String.valueOf(subscription.getUserId()));
            pstmt.setString(2,String.valueOf(subscription.getBookId()));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                log.error("Subs creation is failed");
            } else {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) subscription.setId(generatedKeys.getInt(1));
                    else log.error("Failed to create sub, no obtained id");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            subscription.setId(-1);
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subscription;
    }

    @Override
    public void update(Subscription oldSubscription, Subscription newSubscription) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.EDIT_SUBSCRIPTION.query());
            pstmt.setString(1,String.valueOf(newSubscription.getStart()));
            pstmt.setString(2,String.valueOf(newSubscription.getEnd()));
            pstmt.setString(3,String.valueOf(newSubscription.getUserId()));
            pstmt.setString(4,String.valueOf(newSubscription.getBookId()));
            pstmt.setString(5,String.valueOf(oldSubscription.getId()));
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Subscription subscription) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(QUERY.DELETE_SUBSCRIPTION.query());
            stmt.setString(1,String.valueOf(subscription.getId()));
            stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Subscription> getUserAll(User user) {
        List<Subscription> subs=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_USER_ALL_SUBSCRIPTION.query());
            pstmt.setString(1,String.valueOf(user.getId()));
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                subs.add(new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subs;
    }

    @Override
    public void setEndTime(Subscription subscription,String endTime) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.SET_SUBSCRIPTION_END.query());
            pstmt.setString(1,endTime);
            pstmt.setString(2,String.valueOf(subscription.getId()));
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Subscription getSubscriptionByUserAndBookId(String user_id, String book_id) {
        Subscription subscription=null;
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_SUBSCRIPTION_BY_USER_ID_AND_BOOK_ID.query());
            pstmt.setString(1,user_id);
            pstmt.setString(2,book_id);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                subscription = new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subscription;
    }

    @Override
    public List<Subscription> sortByDebt() {
        List<Subscription> subs=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.GET_ALL_SUBSCRIPTION_ORDER_DEBT.query());
            while (rs.next()){
                subs.add(new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return subs;
    }

    @Override
    public List<Subscription> sortByDateNew() {
        List<Subscription> subs=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.GET_ALL_SUBSCRIPTION_ORDER_START_NEW.query());
            while (rs.next()){
                subs.add(new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return subs;
    }

    @Override
    public List<Subscription> sortByDateOld() {
        List<Subscription> subs=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.GET_ALL_SUBSCRIPTION_ORDER_START_OLD.query());
            while (rs.next()){
                subs.add(new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return subs;
    }
    @Override
    public List<Subscription> sortByDateEnd() {
        List<Subscription> subs=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.GET_ALL_SUBSCRIPTION_ORDER_END.query());
            while (rs.next()){
                subs.add(new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return subs;
    }

    @Override
    public List<Subscription> searchByUser(String user_id) {
        List<Subscription> subs=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_SUBSCRIPTION_BY_USER_ID.query());
            pstmt.setString(1,user_id);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                subs.add(new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return subs;
    }

    @Override
    public List<Subscription> searchByBook(String book_id) {
        List<Subscription> subs=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_SUBSCRIPTION_BY_BOOK_ID.query());
            pstmt.setString(1,book_id);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                subs.add(new Subscription(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3), rs.getInt(4),rs.getInt(5),rs.getInt(6)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return subs;
    }
}
