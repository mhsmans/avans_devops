package Domain.Observer;

import Domain.Decorator.EmailDecorator;
import Domain.Decorator.Notifier;
import Domain.Decorator.SlackDecorator;
import Domain.Role;

public class Member implements NotificationSubscriber {
    private String name;
    private String slack;
    private String email;
    private Role role;
    private Boolean emailEnabled;
    private Boolean slackEnabled;
    private Notifier notifier;

    public Member(String name, String slack, String email, Role role, Boolean emailEnabled, Boolean slackEnabled) {
        this.name = name;
        this.slack = slack;
        this.email = email;
        this.role = role;
        this.emailEnabled = emailEnabled;
        this.slackEnabled = slackEnabled;
        this.setNotifier();
    }

    @Override
    public void update(String message) {
        if (notifier != null) {
            notifier.sendMessage(message);
        }
    }

    // set communication channels based on member preference
    public void setNotifier() {
        notifier = new Notifier();
        if (emailEnabled) {
            notifier = new EmailDecorator(notifier, email);
        }
        if (slackEnabled) {
            notifier = new SlackDecorator(notifier, slack);
        }
    }

    public String getSlack() {
        return slack;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
