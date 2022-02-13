package entity.receipt;

public enum ReceiptStatus {

    EXPECTED("expected"),
    SUBSCRIPTION("subscription"),
    COMPLETED("completed"),
    DENIED("denied");

    ReceiptStatus(String status) {
    }
}
