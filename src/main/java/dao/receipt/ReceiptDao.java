package dao.receipt;

import dao.DAO;
import entity.Book;
import entity.receipt.Receipt;
import entity.user.User;

import java.util.List;

public interface ReceiptDao extends DAO<Receipt> {
    Receipt createReceipt(User user, Book book);
    Receipt changeReceiptStatus(Receipt receipt,int status);
    Receipt getReceiptByUserAndBookId(String user_id, String book_id);
    List<Receipt> getReceiptsByUserId(User user);
    List<Receipt> getAll(String status);
}
