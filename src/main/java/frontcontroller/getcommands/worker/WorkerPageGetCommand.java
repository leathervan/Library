package frontcontroller.getcommands.worker;

import dao.receipt.ReceiptDaoImpl;
import entity.Book;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import frontcontroller.ServletCommand;
import service.ReceiptService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkerPageGetCommand implements ServletCommand {
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
        if(req.getParameter("datetime")!=null) System.out.println(req.getParameter("datetime"));
        HttpSession session= req.getSession();
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
        req.setAttribute("countPage",((receipts.size()/countPage)+1));//pagination view
        req.setAttribute("receipts",receipts.stream().limit(countPage).collect(Collectors.toList()));//start
        if(req.getParameter("page") != null){
            int number = Integer.valueOf(req.getParameter("page"));
            List<Receipt> paginationReceipts=receipts.stream().skip((number-1)*countPage).limit(countPage).collect(Collectors.toList());
            req.setAttribute("receipts",paginationReceipts);
        }
    }
}
