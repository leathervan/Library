package frontcontroller.getcommands.user;

import dao.receipt.ReceiptDaoImpl;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import frontcontroller.ServletCommand;
import org.apache.log4j.Logger;
import service.ReceiptService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RejectOrderGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(RejectOrderGetCommand.class);
    private ReceiptService receiptService;
    private static String rejectOrderPage;

    public RejectOrderGetCommand(){
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        rejectOrderPage = properties.getProperty("orderRejected");
    }
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing order reject page GET command");
        String resultPage = rejectOrderPage;
        String receiptID = req.getParameter("receiptID");
        if(receiptID != null && receiptID.length()>0) {
            Receipt receipt=receiptService.getReceipt(Long.valueOf(receiptID));
            if(receipt.getStatus() == ReceiptStatus.EXPECTED.ordinal()) receiptService.changeStatus(receipt, ReceiptStatus.DENIED.ordinal());
        }
        return resultPage;
    }
}
