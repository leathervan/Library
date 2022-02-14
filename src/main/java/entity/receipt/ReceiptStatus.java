package entity.receipt;

public enum ReceiptStatus {

    RECEIPT("receipt"),
    EXPECTED("expected"),
    SUBSCRIPTION("subscription"),
    COMPLETED("completed"),
    DENIED("denied");


    private final String name;
    ReceiptStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
