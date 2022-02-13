package entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Subscription {
    private Integer id;
    private Timestamp start;
    private Timestamp end;
    private Integer debt;
    private Integer user_id;
    private Integer book_id;

    public Subscription(Integer id, Timestamp start, Timestamp end, Integer debt, Integer user_id, Integer book_id) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.debt = debt;
        this.user_id = user_id;
        this.book_id = book_id;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", debt=" + debt +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
