package dao.subscription;

import dao.DAO;
import entity.Subscription;
import entity.user.User;

import java.util.List;

public interface SubscriptionDao extends DAO<Subscription> {
    List<Subscription> getUserAll(User user);
}
