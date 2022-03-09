package service;

import dao.book.BookDao;
import dao.subscription.SubscriptionDao;
import entity.Book;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.user.User;
import org.apache.log4j.Logger;

import java.util.List;

public class SubscriptionService {

    private static final Logger log = Logger.getLogger(SubscriptionService.class);
    private final SubscriptionDao subscriptionDao;

    public SubscriptionService(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }
    public Subscription createSubscription(Subscription subscription){
        log.info("Creating a subscription");
        return subscriptionDao.create(subscription);
    }

    public void deleteSubscription(Subscription subscription){
        log.info("Deleting a subscription");
        subscriptionDao.delete(subscription);
    }

    public boolean editSubscription(Subscription oldSubscription,Subscription newSubscription){
        log.info("Editing a subscription");
        subscriptionDao.update(oldSubscription,newSubscription);
        return subscriptionDao.get(oldSubscription.getId()).equals(newSubscription);
    }

    public Subscription getSubscription(long id){
        log.info("Getting a subscription");
        return subscriptionDao.get(id);
    }

    public List<Subscription> getAllSubscription(){
        log.info("Getting all subscriptions");
        return subscriptionDao.getAll();
    }
    public List<Subscription> getUserSubscription(User user){
        log.info("Getting user's subscriptions");
        return subscriptionDao.getUserAll(user);
    }
    public void setEndTime(Subscription subscription,String endTime){
        log.info("Updating subscription's time of end");
        subscriptionDao.setEndTime(subscription,endTime);
    }
    public Subscription getSubscriptionByUserAndBookId(String user_id, String book_id) {
        log.info("Getting a subscription by user and book");
        return subscriptionDao.getSubscriptionByUserAndBookId(user_id, book_id);
    }

    public List<Subscription> sortByDebt() {
        log.info("Sorting a subscriptions by debt");
        return subscriptionDao.sortByDebt();
    }

    public List<Subscription> sortByDateNew() {
        log.info("Sorting a subscriptions by new");
        return subscriptionDao.sortByDateNew();
    }

    public List<Subscription> sortByDateOld() {
        log.info("Sorting a subscriptions by old");
        return subscriptionDao.sortByDateOld();
    }
    public List<Subscription> sortByDateEnd() {
        log.info("Sorting a subscriptions by time of end");
        return subscriptionDao.sortByDateEnd();
    }
    public List<Subscription> searchByUserId(String user_id){
        log.info("Searching a subscriptions by user");
        return subscriptionDao.searchByUser(user_id);
    }
    public List<Subscription> searchByBookId(String book_id){
        log.info("Searching a subscriptions by book");
        return subscriptionDao.searchByBook(book_id);
    }
}
