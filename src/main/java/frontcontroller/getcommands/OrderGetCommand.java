package frontcontroller.getcommands;

import dao.book.BookDaoImpl;
import dao.receipt.ReceiptDaoImpl;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.receipt.Receipt;
import entity.user.User;
import frontcontroller.ServletCommand;
import service.BookService;
import service.ReceiptService;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderGetCommand implements ServletCommand {
    private static String orderPage;
    private static String orderExistPage;
    private ReceiptService receiptService;
    private BookService bookService;
    private UserService userService;
    public OrderGetCommand(){
        receiptService=new ReceiptService(ReceiptDaoImpl.getInstance());
        bookService = new BookService(BookDaoImpl.getInstance());
        userService=new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        orderPage = properties.getProperty("orderAccepted");
        orderExistPage = properties.getProperty("orderExist");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userID = req.getParameter("userID");
        String bookID=req.getParameter("bookID");
        if(userID != null && bookID != null && userID.length()>0 && bookID.length()>0){
            Book book=bookService.getBook(Integer.valueOf(bookID));
            User user=userService.getUserById(Integer.valueOf(userID));
            Receipt receipt=receiptService.getReceiptByUserAndBookId(userID,bookID);
            if(receipt==null) {
                receiptService.createReceipt(user,book);
                return orderPage;
            }
        }
        return orderExistPage;
    }
}
