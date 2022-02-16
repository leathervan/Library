package frontcontroller.getcommands.worker;

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

public class ApproveOrderGetCommand implements ServletCommand {
    private UserService userService;
    private BookService bookService;
    private ReceiptService receiptService;
    private SubscriptionService subscriptionService;
    private static String approveOrderPage;
    private static String bookNotAvailablePage;

    public ApproveOrderGetCommand() {
        userService =new UserService(UserDaoImpl.getInstance());
        bookService=new BookService(BookDaoImpl.getInstance());
        receiptService=new ReceiptService(ReceiptDaoImpl.getInstance());
        subscriptionService=new SubscriptionService(SubscriptionDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        approveOrderPage = properties.getProperty("approveOrderPage");
        bookNotAvailablePage = properties.getProperty("bookNotAvailablePage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String resultpage = approveOrderPage;
        String receipt_id = req.getParameter("receiptID");
        Receipt receipt=receiptService.getReceipt(Integer.valueOf(receipt_id));
        Book book=bookService.getBook(receipt.getBook_id());
        User user=userService.getUserById(receipt.getUser_id());
        Subscription subscription = subscriptionService.getSubscriptionByUserAndBookId(user.getId().toString(),book.getId().toString());
        if(subscription == null && book.getAmount()>0) subscription = subscriptionService.createSubscription(new Subscription(-1,user.getId(), book.getId()));
        else resultpage = bookNotAvailablePage;
        req.setAttribute("receipt",receipt);
        req.setAttribute("customer",user);
        req.setAttribute("book",book);
        req.setAttribute("sub",subscription);
        return resultpage;
    }
}
