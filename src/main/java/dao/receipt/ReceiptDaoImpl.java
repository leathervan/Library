package dao.receipt;

import connection.MyConnectionPool;
import dao.QUERY;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDaoImpl implements ReceiptDao{
    private MyConnectionPool connectionPool;

    private ReceiptDaoImpl() {
        connectionPool = MyConnectionPool.getInstance();
    }

    private static ReceiptDaoImpl instance;
    public static ReceiptDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReceiptDaoImpl();
        }
        return instance;
    }

    @Override
    public Receipt get(long id) {
        Receipt receipt=null;
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_RECEIPT.query());
            pstmt.setString(1,String.valueOf(id));
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                receipt = new Receipt(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return receipt;
    }
    @Override
    public List<Receipt> getAll() {
        Connection connection= connectionPool.getConnection();
        List<Receipt> receipts=new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.GET_ALL_RECEIPT.query());
            while (rs.next()){
                receipts.add(new Receipt(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return receipts;
    }

    @Override
    public List<Receipt> getAll(String status) {
        Connection connection= connectionPool.getConnection();
        List<Receipt> receipts=new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_ALL_RECEIPT_BY_STATUS.query());
            pstmt.setString(1,status);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                receipts.add(new Receipt(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return receipts;
    }

    @Override
    public Receipt create(Receipt receipt) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.CREATE_RECEIPT.query(),Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,String.valueOf(receipt.getUser_id()));
            pstmt.setString(2,String.valueOf(receipt.getBook_id()));
            pstmt.setString(3,String.valueOf(ReceiptStatus.EXPECTED.ordinal()));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Receipt creation is failed");
            } else {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        receipt.setId(generatedKeys.getInt(1));
                    } else {
                        System.out.println("Failed to create user, no obtained id");
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            receipt.setId(-1);
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return receipt;
    }

    @Override
    public void update(Receipt oldReceipt, Receipt newReceipt) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.EDIT_RECEIPT.query());
            pstmt.setString(1,String.valueOf(newReceipt.getUser_id()));
            pstmt.setString(2,String.valueOf(newReceipt.getBook_id()));
            pstmt.setString(3,String.valueOf(newReceipt.getStatus()));
            pstmt.setString(5,String.valueOf(oldReceipt.getId()));
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Receipt receipt) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(QUERY.DELETE_RECEIPT.query());
            stmt.setString(1,String.valueOf(receipt.getId()));
            stmt.executeUpdate();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Receipt createReceipt(User user, Book book) {
        return  create(new Receipt(-1, user.getId(), book.getId(), 1));
    }

    @Override
    public Receipt changeReceiptStatus(Receipt receipt,int status) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.CHANGE_RECEIPT_STATUS.query(),Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, String.valueOf(status));
            pstmt.setString(2, String.valueOf(receipt.getId()));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Receipt creation is failed");
            } else {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        receipt.setId(generatedKeys.getInt(1));
                    } else {
                        System.out.println("Failed to create user, no obtained id");
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            receipt.setId(-1);
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        receipt.setStatus(status);
        return receipt;
    }

    @Override
    public Receipt getReceiptByUserAndBookId(String user_id, String book_id) {
        Receipt receipt=null;
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_RECEIPT_BY_USER_ID_AND_BOOK_ID.query());
            pstmt.setString(1,user_id);
            pstmt.setString(2,book_id);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                receipt=new Receipt(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return receipt;
    }

    @Override
    public List<Receipt> getReceiptsByUserId(User user) {
        List<Receipt> receipts=new ArrayList<>();
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.GET_RECEIPT_BY_USER_ID.query());
            pstmt.setString(1,String.valueOf(user.getId()));
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                receipts.add(new Receipt(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return receipts;
    }
}
