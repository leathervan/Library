package dao.subscription;

import dao.DAO;
import entity.Subscription;
import entity.receipt.Receipt;
import entity.user.User;

import java.util.List;

public interface SubscriptionDao extends DAO<Subscription> {
    List<Subscription> getUserAll(User user);
    void setEndTime(Subscription subscription,String endTime);
    Subscription getSubscriptionByUserAndBookId(String user_id, String book_id);
}
