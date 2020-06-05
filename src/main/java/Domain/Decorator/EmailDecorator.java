package Domain.Decorator;

public class EmailDecorator extends MessageDecorator {

    private String address;

    public EmailDecorator(Notifier notifier, String emailAddress) {
        super(notifier);
        this.address = emailAddress;
    }

    @Override
    public void sendMessage(String message) {
        super.sendMessage(message);
        sendEmail(message);
    }

    public void sendEmail(String message) {
        System.err.println("Sending email to " + address + " : " + message);
    }
}
