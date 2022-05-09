package entity;

import java.sql.Timestamp;

public class Subscription {
    private Integer id;
    private Timestamp start;
    private Timestamp end;
    private Integer debt;
    private Integer userId;
    private Integer bookId;

    public Subscription(Integer id, Timestamp start, Timestamp end, Integer debt, Integer userId, Integer bookId) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.debt = debt;
        this.userId = userId;
        this.bookId = bookId;
    }
    public Subscription(Integer id, Integer userId, Integer bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", debt=" + debt +
                ", user_id=" + userId +
                ", book_id=" + bookId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Integer getDebt() {
        return debt;
    }

    public void setDebt(Integer debt) {
        this.debt = debt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
