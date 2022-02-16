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

    public boolean deleteSubscription(Subscription subscription){
        int size=subscriptionDao.getAll().size();
        subscriptionDao.delete(subscription);
        return subscriptionDao.get(subscription.getId())==null && subscriptionDao.getAll().size()!=size;
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
}
