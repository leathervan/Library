package frontcontroller.getcommands;

import dao.book.BookDaoImpl;
import dao.receipt.ReceiptDaoImpl;
import dao.subscription.SubscriptionDaoImpl;
import dao.user.UserDaoImpl;
import entity.Book;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.user.User;
import frontcontroller.ServletCommand;
import service.BookService;
import service.ReceiptService;
import service.SubscriptionService;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReturnBookGetCommand implements ServletCommand{
    private static String bookReturnPage;
    private SubscriptionService subscriptionService;
    private BookService bookService;
    public ReturnBookGetCommand(){
        subscriptionService = new SubscriptionService(SubscriptionDaoImpl.getInstance());
        bookService=new BookService(BookDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        bookReturnPage = properties.getProperty("bookReturned");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String subID = req.getParameter("subID");
        if(subID != null && subID.length()>0){
            Subscription subscription=subscriptionService.getSubscription(Long.valueOf(subID));
            bookService.increaseBookAmount(bookService.getBook(subscription.getBook_id()));
            subscriptionService.deleteSubscription(subscription);
        }
        return bookReturnPage;
    }
}
