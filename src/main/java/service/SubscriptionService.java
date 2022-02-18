package service;

import dao.book.BookDao;
import dao.subscription.SubscriptionDao;
import entity.Book;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.user.User;

import java.util.List;

public class SubscriptionService {
    private final SubscriptionDao subscriptionDao;

    public SubscriptionService(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }
    public Subscription createSubscription(Subscription subscription){
        return subscriptionDao.create(subscription);
    }

    public void deleteSubscription(Subscription subscription){
        subscriptionDao.delete(subscription);
    }

    public boolean editSubscription(Subscription oldSubscription,Subscription newSubscription){
        subscriptionDao.update(oldSubscription,newSubscription);
        return subscriptionDao.get(oldSubscription.getId()).equals(newSubscription);
    }

    public Subscription getSubscription(long id){
        return subscriptionDao.get(id);
    }

    public List<Subscription> getAllSubscription(){
        return subscriptionDao.getAll();
    }
    public List<Subscription> getUserSubscription(User user){
        return subscriptionDao.getUserAll(user);
    }
    public void setEndTime(Subscription subscription,String endTime){
        subscriptionDao.setEndTime(subscription,endTime);
    }
    public Subscription getSubscriptionByUserAndBookId(String user_id, String book_id) {;
        return subscriptionDao.getSubscriptionByUserAndBookId(user_id, book_id);
    }

    public List<Subscription> sortByDebt() {
        return subscriptionDao.sortByDebt();
    }

    public List<Subscription> sortByDateNew() {
        return subscriptionDao.sortByDateNew();
    }

    public List<Subscription> sortByDateOld() {
        return subscriptionDao.sortByDateOld();
    }
    public List<Subscription> sortByDateEnd() {
        return subscriptionDao.sortByDateEnd();
    }
    public List<Subscription> searchByUserId(String user_id){
        return subscriptionDao.searchByUser(user_id);
    }
    public List<Subscription> searchByBookId(String book_id){
        return subscriptionDao.searchByBook(book_id);
    }
}
