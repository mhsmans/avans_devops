package Domain.Decorator;

public class SlackDecorator extends MessageDecorator{

    private String address;

    public SlackDecorator(Notifier notifier, String slackAddress) {
        super(notifier);
        this.address = slackAddress;
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(message);
        sendSlackMessage(message);
    }

    public void sendSlackMessage(String message) {
        System.err.println("Sending slack to " + address + " : " + message);
    }
}
