package service;

import dao.book.BookDao;
import dao.subscription.SubscriptionDao;
import entity.Book;
import entity.Subscription;

import java.util.List;

public class SubscriptionService {
    private final SubscriptionDao subscriptionDao;

    public SubscriptionService(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }
    public boolean createSubscription(Subscription subscription){
        return subscription != null && subscriptionDao.create(subscription).getId() != -1;
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
}
