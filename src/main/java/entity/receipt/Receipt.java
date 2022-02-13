package entity.receipt;

import java.util.Objects;

public class Receipt {
    private Integer id;
    private Integer user_id;
    private Integer book_id;
    private Integer status;

    public Receipt(Integer id, Integer user_id, Integer book_id, Integer status) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", user_id=" + user_id +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return id.equals(receipt.id) && user_id.equals(receipt.user_id) && book_id.equals(receipt.book_id) && status.equals(receipt.status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
