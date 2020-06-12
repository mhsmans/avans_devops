package Domain.Decorator;

public abstract class MessageDecorator implements Notifier {

    private Notifier wrappee;

    public MessageDecorator(Notifier notifier) {
        this.wrappee = notifier;
    }

    @Override
    public void sendMessage(String message) {
        this.wrappee.sendMessage(message);
    }
}
