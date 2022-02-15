package frontcontroller.getcommands;

import dao.receipt.ReceiptDaoImpl;
import dao.subscription.SubscriptionDaoImpl;
import dao.user.UserDaoImpl;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.user.User;
import frontcontroller.ServletCommand;
import service.ReceiptService;
import service.SubscriptionService;
import service.UserService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserProfileGetCommand implements ServletCommand{
    private SubscriptionService subscriptionService;
    private UserService userService;
    private ReceiptService receiptService;
    private static String userProfile;

    public UserProfileGetCommand() {
        subscriptionService=new SubscriptionService(SubscriptionDaoImpl.getInstance());
        userService=new UserService(UserDaoImpl.getInstance());
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        userProfile = properties.getProperty("userProfile");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user=userService.getUserById(Integer.valueOf(session.getAttribute("id").toString()));
        List<Subscription> subs=subscriptionService.getUserSubscription(user);
        List<Receipt> receipts=receiptService.getReceiptsByUserId(user);
        req.setAttribute("subs",subs);
        req.setAttribute("receipts",receipts);
        return userProfile;
    }
}
