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
        List<Receipt> receipts=new ArrayList<>();;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs= stmt.executeQuery(QUERY.GET_ALL_RECEIPT.query());
            while (rs.next()){
                receipts.add(new Receipt(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4)));
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return receipts;
    }

    @Override
    public Receipt create(Receipt receipt) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.CREATE_RECEIPT.query());
            pstmt.setString(1,String.valueOf(receipt.getId()));
            pstmt.setString(2,String.valueOf(receipt.getUser_id()));
            pstmt.setString(3,String.valueOf(receipt.getBook_id()));
            pstmt.setString(4,String.valueOf(receipt.getStatus()));
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
    public Receipt changeReceiptStatus(Receipt receipt,String status) {
        Connection connection= connectionPool.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement(QUERY.CREATE_RECEIPT.query());
            pstmt.setString(1, String.valueOf(ReceiptStatus.valueOf(status).ordinal()));
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
        receipt.setStatus(ReceiptStatus.valueOf(status).ordinal());
        return receipt;
    }
}
