package frontcontroller.getcommands.user;

import dao.book.BookDaoImpl;
import dao.receipt.ReceiptDaoImpl;
import dao.subscription.SubscriptionDaoImpl;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import entity.user.User;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.BookService;
import service.ReceiptService;
import service.SubscriptionService;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReturnBookGetCommand implements ServletCommand{

    private static final Logger log = Logger.getLogger(ReturnBookGetCommand.class);
    private static String bookReturnPage;
    private SubscriptionService subscriptionService;
    private BookService bookService;
    private ReceiptService receiptService;
    private UserService userService;
    public ReturnBookGetCommand(){
        subscriptionService = new SubscriptionService(SubscriptionDaoImpl.getInstance());
        bookService=new BookService(BookDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        userService=new UserService(UserDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        bookReturnPage = properties.getProperty("bookReturned");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing book return page GET command");
        String subID = req.getParameter("subID");
        if(subID != null && subID.length()>0){
            Subscription subscription = subscriptionService.getSubscription(Long.valueOf(subID));
            User user = userService.getUserById(subscription.getUserId());
            Book book = bookService.getBook(subscription.getBookId());
            Receipt receipt=receiptService.getReceiptByUserAndBookId(user.getId().toString(),book.getId().toString());
            receiptService.changeStatus(receipt, ReceiptStatus.COMPLETED.ordinal());
            bookService.increaseBookAmount(bookService.getBook(subscription.getBookId()));
            subscriptionService.deleteSubscription(subscription);
        }
        return bookReturnPage;
    }
}
