package service;

import dao.book.BookDao;
import dao.receipt.ReceiptDao;
import entity.Book;
import entity.receipt.Receipt;
import entity.user.User;
import org.apache.log4j.Logger;

import java.util.List;

public class ReceiptService {

    private static final Logger log = Logger.getLogger(ReceiptService.class);
    private final ReceiptDao receiptDao;

    public ReceiptService(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }
    public Receipt createReceipt(Receipt receipt){
        log.info("Creating a receipt");
        return receiptDao.create(receipt);
    }

    public boolean deleteReceipt(Receipt receipt){
        log.info("Deleting a receipt");
        int size=receiptDao.getAll().size();
        receiptDao.delete(receipt);
        return receiptDao.get(receipt.getId())==null && receiptDao.getAll().size()!=size;
    }

    public boolean editReceipt(Receipt oldReceipt,Receipt newReceipt){
        log.info("Editing a receipt");
        receiptDao.update(oldReceipt,newReceipt);
        return receiptDao.get(oldReceipt.getId()).equals(newReceipt);
    }
    public Receipt createReceipt(User user,Book book){
        log.info("Creating a receipt by user and book");
        return receiptDao.createReceipt(user,book);
    }
    public Receipt getReceipt(long id){
        log.info("Getting a receipt");
        return receiptDao.get(id);
    }
    public List<Receipt> getAllReceipt(){
        log.info("Getting all receipts");
        return receiptDao.getAll();
    }
    public List<Receipt> getAllReceipt(String status){
        log.info("Getting all receipts by status");
        return receiptDao.getAll(status);
    }
    public Receipt changeStatus(Receipt receipt,int status){
        log.info("Changing receipt's status");
        return receiptDao.changeReceiptStatus(receipt,status);
    }
    public Receipt getReceiptByUserAndBookId(String user_id, String book_id) {
        log.info("Getting receipt by user and book");
        return receiptDao.getReceiptByUserAndBookId(user_id, book_id);
    }
    public List<Receipt> getReceiptsByUserId(User user) {
        log.info("Getting receipt by user");
        return receiptDao.getReceiptsByUserId(user);
    }
}
