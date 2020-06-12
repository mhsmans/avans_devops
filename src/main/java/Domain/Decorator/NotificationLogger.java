package Domain.Decorator;

public class NotificationLogger implements Notifier {

    private String receiverName;

    public NotificationLogger(String name) {
        receiverName = name;
    }

    @Override
    public void sendMessage(String message) {
        System.err.println("MESSAGE LOG : " + message + " sent to receiver: " + receiverName);
    }
}
