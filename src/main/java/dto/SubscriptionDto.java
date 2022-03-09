package dto;

import dao.book.BookDaoImpl;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.Subscription;
import entity.user.User;
import service.BookService;
import service.UserService;

import java.sql.Timestamp;

public class SubscriptionDto {
    private Integer id;
    private Timestamp start;
    private Timestamp end;
    private Integer debt;
    private User user;
    private Book book;

    public SubscriptionDto(Subscription subscription){
        UserService userService = new UserService(UserDaoImpl.getInstance());
        BookService bookService = new BookService(BookDaoImpl.getInstance());

        id = subscription.getId();
        start = subscription.getStart();
        end = subscription.getEnd();
        debt = subscription.getDebt();
        user = userService.getUserById(subscription.getUser_id());
        book = bookService.getBook(subscription.getBook_id());
    }

    @Override
    public String toString() {
        return "SubscriptionDto{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", debt=" + debt +
                ", user=" + user +
                ", book=" + book +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public Integer getDebt() {
        return debt;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }
}
