package Domain;

import Domain.Observer.Member;

public class Activity {

    private String title;
    private Member assignedTo;
    private String description;
    private Boolean completed;

    public Activity(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public void assignMember(Member member) {
        if (assignedTo == null) {
            assignedTo = member;
        } else {
            System.err.println("activity is already assigned to a member");
        }
    }

    public void markComplete() {
        if (assignedTo != null) {
            completed = true;
        } else {
            System.err.println("cannot mark complete without an assigned member");
        }
    }

    public Boolean getCompleted() {
        return completed;
    }
}
