package dao.receipt;

import dao.DAO;
import entity.Book;
import entity.receipt.Receipt;
import entity.user.User;

public interface ReceiptDao extends DAO<Receipt> {
    Receipt createReceipt(User user, Book book);
    Receipt changeReceiptStatus(Receipt receipt,String status);
}
