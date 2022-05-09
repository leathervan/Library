package entity.receipt;

public class Receipt {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer status;

    public Receipt(Integer id, Integer userId, Integer bookId, Integer status) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", book_id=" + bookId +
                ", user_id=" + userId +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return id.equals(receipt.id) && userId.equals(receipt.userId) && bookId.equals(receipt.bookId) && status.equals(receipt.status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
