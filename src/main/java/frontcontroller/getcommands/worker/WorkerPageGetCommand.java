package frontcontroller.getcommands.worker;

import dao.receipt.ReceiptDaoImpl;
import dto.ReceiptDto;
import dto.SubscriptionDto;
import entity.Book;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import frontcontroller.ServletCommand;
import frontcontroller.getcommands.admin.WorkersGetCommand;
import frontcontroller.getcommands.user.UserProfileGetCommand;
import org.apache.log4j.Logger;
import service.ReceiptService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerPageGetCommand implements ServletCommand {

    private static final Logger log = Logger.getLogger(WorkersGetCommand.class);
    private ReceiptService receiptService;
    private static String workerPage;
    private static String status;
    private static int countPage = 10;

    public WorkerPageGetCommand() {
        receiptService = new ReceiptService(ReceiptDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        workerPage = properties.getProperty("workerPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("Executing worker page GET command");
        List<Receipt> receipts = receiptService.getAllReceipt();
        pagination(req,receipts);
        return workerPage;
    }
    private void pagination(HttpServletRequest req,List<Receipt> receipts){
        if(req.getParameter("status") == null && status!=null) {
            if("ALL".equals(status)) receipts = receiptService.getAllReceipt();
            else receipts = receiptService.getAllReceipt(String.valueOf(ReceiptStatus.valueOf(status).ordinal()));
        }
        if(req.getParameter("status") != null) {
            status=req.getParameter("status");
            if("ALL".equals(status)) receipts = receiptService.getAllReceipt();
            else receipts = receiptService.getAllReceipt(String.valueOf(ReceiptStatus.valueOf(status).ordinal()));
        }
        List<ReceiptDto> receiptDtos=new ArrayList<>();
        convertToReceiptDto(receipts,receiptDtos);
        setPage(req,receiptDtos);
    }

    private void convertToReceiptDto(List<Receipt> receipts, List<ReceiptDto> receiptDtos){
        for (Receipt receipt: receipts){
            receiptDtos.add(new ReceiptDto(receipt));
        }
    }

    private void setPage(HttpServletRequest req,List<ReceiptDto> receiptDtos){
        req.setAttribute("countPage",((receiptDtos.size()/countPage)+1));

        if(req.getParameter("page") != null){
            int number = Integer.valueOf(req.getParameter("page"));
            receiptDtos = receiptDtos.stream().skip((number-1)*countPage).limit(countPage).collect(Collectors.toList());
        }
        else receiptDtos = receiptDtos.stream().limit(countPage).collect(Collectors.toList());

        req.setAttribute("receipts",receiptDtos);
    }
}
