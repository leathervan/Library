package frontcontroller.getcommands.worker;

import dao.receipt.ReceiptDaoImpl;
import dao.subscription.SubscriptionDaoImpl;
import dto.SubscriptionDto;
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
        List<SubscriptionDto> subscriptionDtos=new ArrayList<>();
        sortSubs(req,subs,subscriptionDtos);
        searchSubs(req,subs,subscriptionDtos);
        return subsPage;
    }

    private void sortSubs(HttpServletRequest req,List<Subscription> subs,List<SubscriptionDto> subscriptionDtos){
        if(req.getParameter("sort") == null && sort!=null) checkSortParameter(subs,sort);
        if(req.getParameter("sort") != null) {
            sort = req.getParameter("sort");
            subs = checkSortParameter(subs,sort);
        }
        convertToSubscriptionDto(subs,subscriptionDtos);
        req.setAttribute("subs",subscriptionDtos);
    }

    private void searchSubs(HttpServletRequest req,List<Subscription> subs,List<SubscriptionDto> subscriptionDtos){
        String search = req.getParameter("searchuser");
        if(search != null && search.length()>0) {
            subs = subscriptionService.searchByUserId(search);
            subscriptionDtos.clear();
            convertToSubscriptionDto(subs,subscriptionDtos);
            req.setAttribute("subs",subscriptionDtos);
        }
        search = req.getParameter("searchbook");
        if(search != null && search.length()>0) {
            subs = subscriptionService.searchByBookId(search);
            subscriptionDtos.clear();
            convertToSubscriptionDto(subs,subscriptionDtos);
            req.setAttribute("subs",subscriptionDtos);;
        }
    }

    private void convertToSubscriptionDto(List<Subscription> subscriptions, List<SubscriptionDto> subscriptionDtos){
        for (Subscription subscription: subscriptions){
            subscriptionDtos.add(new SubscriptionDto(subscription));
        }
    }

    private List<Subscription> checkSortParameter(List<Subscription> subs, String sort){
        switch (sort) {
            case "new":
                subs=subscriptionService.sortByDateNew();
                break;
            case "old":
                subs=subscriptionService.sortByDateOld();
                break;
            case "end":
                subs=subscriptionService.sortByDateEnd();
                break;
            case "debt":
                subs=subscriptionService.sortByDebt();
                break;
        }
        return subs;
    }
}
