package dao.subscription;

import connection.MyConnectionPool;
import dao.QUERY;
import dao.receipt.ReceiptDaoImpl;
import entity.Subscription;
import entity.receipt.Receipt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao{
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
            PreparedStatement pstmt = connection.prepareStatement(QUERY.CREATE_SUBSCRIPTION.query());
            pstmt.setString(1,String.valueOf(subscription.getEnd()));
            pstmt.setString(2,String.valueOf(subscription.getUser_id()));
            pstmt.setString(3,String.valueOf(subscription.getBook_id()));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Receipt creation is failed");
            } else {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        subscription.setId(generatedKeys.getInt(1));
                    } else {
                        System.out.println("Failed to create user, no obtained id");
                    }
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
            pstmt.setString(1,String.valueOf(newSubscription.getEnd()));
            pstmt.setString(2,String.valueOf(newSubscription.getUser_id()));
            pstmt.setString(3,String.valueOf(newSubscription.getBook_id()));
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
}
