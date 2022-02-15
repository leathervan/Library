package frontcontroller.getcommands;

import dao.book.BookDaoImpl;
import dao.receipt.ReceiptDaoImpl;
import dao.subscription.SubscriptionDaoImpl;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import frontcontroller.ServletCommand;
import service.BookService;
import service.ReceiptService;
import service.SubscriptionService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RejectOrderGetCommand implements ServletCommand {
    private static String rejectOrderPage;
    private ReceiptService receiptService;

    public RejectOrderGetCommand(){
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        rejectOrderPage = properties.getProperty("orderRejected");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String receiptID = req.getParameter("receiptID");
        if(receiptID != null && receiptID.length()>0) {
            Receipt receipt=receiptService.getReceipt(Long.valueOf(receiptID));
            receiptService.changeStatus(receipt, ReceiptStatus.DENIED.ordinal());
        }
        return rejectOrderPage;
    }
}
