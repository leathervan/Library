
import dao.book.BookDaoImpl;
import dao.receipt.ReceiptDaoImpl;
import dao.user.UserDaoImpl;
import entity.*;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import entity.user.User;
import entity.user.UserRole;
import service.BookService;
import service.ReceiptService;
import service.UserService;


import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) {
        String str="2022-02-28 12:00:00";
        Timestamp timestamp= Timestamp.valueOf(str);


        User user=new User(6,"qwerty","qwerty","Bogdan","Oskin",3);
        Book book=new Book(1,"20book1","author1","edition1",2010,2);
    }
}
