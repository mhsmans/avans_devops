package Domain;

import Domain.Observer.Member;
import Domain.StateBacklogItem.BacklogItem;

import java.util.ArrayList;

public class DiscussionThread {

    private String title;
    private ArrayList<Comment> comments;
    private Boolean closed;
    private BacklogItem backlogItem;

    public DiscussionThread(String title, BacklogItem backlogItem) {
        this.title = title;
        this.comments = new ArrayList<>();
        this.backlogItem = backlogItem;
    }

    public void addComment(Member member, String body) {
        this.checkClosed();

        if (closed) {
            System.err.println("discussion is closed");
        } else {
            Comment comment = new Comment(member, body);
            comments.add(comment);
        }
    }

    private void checkClosed() {
        closed = backlogItem.isCompleted();
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
