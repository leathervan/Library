package dao;

public enum QUERY {
    CREATE_USER("insert into user value(default,?,?,?,?,?)"),
    GET_USER("select * from user where id = ?"),
    GET_USER_BY_LOGIN("select * from user where email = ?"),
    GET_USER_BY_LOGIN_AND_PASSWORD("select * from user where email = ? and password = ?"),
    GET_ALL_USERS("select * from user"),
    DELETE_USER("delete from user where id = ?"),
    EDIT_USER("update user set email = ?,password = ?,name = ?, surname = ?,role_id = ? where id = ?"),

    CREATE_BOOK("insert into book value(default,?,?,?,?,?)"),
    GET_BOOK("select * from book where id = ?"),
    GET_ALL_BOOKS("select * from book"),
    DELETE_BOOK("delete from book where id = ?"),
    EDIT_BOOK("update book set name = ?,author = ?,edtion = ?, year_edition = ?,amount = ? where id = ?"),
    SEARCH_BOOK_BY_AUTHOR("select * from book where author=?"),
    SEARCH_BOOK_BY_NAME("select * from book where name=?"),
    SORT_BOOK_BY_NAME("select * from book order by name"),
    SORT_BOOK_BY_AUTHOR("select * from book order by author"),
    SORT_BOOK_BY_EDITION("select * from book order by edition"),
    SORT_BOOK_BY_YEAR_EDITION("select * from book order by year_edition"),

    CREATE_RECEIPT("insert into receipt value(default,?,?,?)"),
    DELETE_RECEIPT("delete from receipt where id = ?"),
    EDIT_RECEIPT("update receipt set user_id = ?,book_id = ?,receipt_status_id = ? where id = ?"),
    GET_ALL_RECEIPT("select * from receipt"),
    GET_RECEIPT("select * from receipt where id = ?"),
    CHANGE_RECEIPT_STATUS("update receipt set receipt_status_id = ? where id = ?"),
    GET_RECEIPT_BY_USER_ID_AND_BOOK_ID("select * from receipt where user_id = ? and book_id = ?"),

    GET_SUBSCRIPTION("select * from subscription where id = ?"),
    DELETE_SUBSCRIPTION("delete from subscription where id = ?"),
    GET_ALL_SUBSCRIPTION("select * from subscription"),
    CREATE_SUBSCRIPTION("insert into subscription(start,end,user_id,book_id) values(sysdate(),?,?,?)"),
    EDIT_SUBSCRIPTION("update subscription set start = ?,end = ?,user_id = ?, book_id = ? where id = ?");


    private final String query;

    QUERY(String query) {
        this.query = query;
    }

    public String query() {
        return query;
    }
}
