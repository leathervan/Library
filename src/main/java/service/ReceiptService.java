package service;

import dao.book.BookDao;
import dao.receipt.ReceiptDao;
import entity.Book;
import entity.receipt.Receipt;
import entity.user.User;

import java.util.List;

public class ReceiptService {
    private final ReceiptDao receiptDao;

    public ReceiptService(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }
    public boolean createReceipt(Receipt receipt){
        return receipt != null && receiptDao.create(receipt).getId() != -1;
    }

    public boolean deleteReceipt(Receipt receipt){
        int size=receiptDao.getAll().size();
        receiptDao.delete(receipt);
        return receiptDao.get(receipt.getId())==null && receiptDao.getAll().size()!=size;
    }

    public boolean editReceipt(Receipt oldReceipt,Receipt newReceipt){
        receiptDao.update(oldReceipt,newReceipt);
        return receiptDao.get(oldReceipt.getId()).equals(newReceipt);
    }
    public boolean createReceipt(User user,Book book){
        return user != null && book!=null && receiptDao.createReceipt(user,book).getId() != -1;
    }
    public Receipt changeStatus(Receipt receipt,String status){
        return receiptDao.changeReceiptStatus(receipt,status);
    }
}
