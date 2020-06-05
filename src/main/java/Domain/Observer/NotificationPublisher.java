package Domain.Observer;

import Domain.Role;

import java.util.ArrayList;

public final class NotificationPublisher {

    private static NotificationPublisher instance;
    private ArrayList<Member> subscribers;

    private NotificationPublisher() {
        subscribers = new ArrayList<>();
    }

    public static NotificationPublisher getNotificationPublisher() {
        if (instance == null) {
            instance = new NotificationPublisher();
        }
        return instance;
    }

    public void subscribe(Member subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        } else {
            System.err.println("Subscriber already exists");
        }
    }

    public void unsubscribe(Member subscriber) {
        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            System.err.println("Subscriber has no subscription");
        }
    }

    public void notifyAllSubscribers(String message) {
        for (Member subscriber : subscribers) {
            subscriber.update(message);
        }
    }

    public void notifyScrumMaster(String message) {
        for (Member subscriber : subscribers) {
            if (subscriber.getRole() == Role.SCRUM_MASTER) {
                subscriber.update(message);
            }
        }
    }

    public void notifyProductOwner(String message) {
        for (Member subscriber : subscribers) {
            if (subscriber.getRole() == Role.PRODUCT_OWNER) {
                subscriber.update(message);
            }
        }
    }

    public ArrayList<Member> getSubscribers() {
        return subscribers;
    }

    public void deleteSubscribers() {
        subscribers.clear();
    }
}
