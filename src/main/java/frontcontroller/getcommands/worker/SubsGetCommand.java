package frontcontroller.getcommands.worker;

import dao.receipt.ReceiptDaoImpl;
import dao.subscription.SubscriptionDaoImpl;
import entity.Book;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.receipt.ReceiptStatus;
import frontcontroller.ServletCommand;
import service.ReceiptService;
import service.SubscriptionService;
import util.MappingProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubsGetCommand implements ServletCommand {
    private SubscriptionService subscriptionService;
    private static String subsPage;
    private static String sort;
    private static int countPage = 10;

    public SubsGetCommand() {
        subscriptionService = new SubscriptionService(SubscriptionDaoImpl.getInstance());
        MappingProperties properties = MappingProperties.getInstance();
        subsPage = properties.getProperty("subsPage");
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Subscription> subs = subscriptionService.getAllSubscription();
        pagination(req,subs);
        searchSubs(req,subs);
        return subsPage;
    }
    private void pagination(HttpServletRequest req,List<Subscription> subs){
        if(req.getParameter("sort") == null && sort!=null) {
            if("new".equals(sort)) subs=subscriptionService.sortByDateNew();
            if("old".equals(sort)) subs=subscriptionService.sortByDateOld();
            if("end".equals(sort)) subs=subscriptionService.sortByDateEnd();
            if("debt".equals(sort)) subs=subscriptionService.sortByDebt();
        }
        if(req.getParameter("sort") != null) {
            sort = req.getParameter("sort");
            if("new".equals(sort)) subs=subscriptionService.sortByDateNew();
            if("old".equals(sort)) subs=subscriptionService.sortByDateOld();
            if("end".equals(sort)) subs=subscriptionService.sortByDateEnd();
            if("debt".equals(sort)) subs=subscriptionService.sortByDebt();
        }
        req.setAttribute("subs",subs);
    }
    private void searchSubs(HttpServletRequest req,List<Subscription> subs){
        String search = req.getParameter("searchuser");
        if(search != null && search.length()>0) {
            subs = subscriptionService.searchByUserId(search);
            req.setAttribute("subs",subs);
        }
        search = req.getParameter("searchbook");
        if(search != null && search.length()>0) {
            subs = subscriptionService.searchByBookId(search);
            req.setAttribute("subs",subs);
        }
    }
}
