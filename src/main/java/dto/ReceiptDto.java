package dto;

import dao.book.BookDaoImpl;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import entity.user.User;
import service.BookService;
import service.UserService;

public class ReceiptDto {
    private Integer id;
    private User user;
    private Book book;
    private ReceiptStatus status;

    public ReceiptDto(Receipt receipt){
        UserService userService = new UserService(UserDaoImpl.getInstance());
        BookService bookService = new BookService(BookDaoImpl.getInstance());
        ReceiptStatus[] receiptStatus = ReceiptStatus.values();

        id = receipt.getId();
        user = userService.getUserById(receipt.getUserId());
        book = bookService.getBook(receipt.getBookId());
        status = receiptStatus[receipt.getStatus()];
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public ReceiptStatus getStatus() {
        return status;
    }
}
