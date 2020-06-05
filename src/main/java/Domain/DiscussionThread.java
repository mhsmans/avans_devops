package Domain;

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
        this.closed = false;
        this.backlogItem = backlogItem;
    }

    public void addComment(Comment comment) {
        if (!comments.contains(comment)) {
            comments.add(comment);
        } else {
            System.err.println("comment already exists");
        }
    }

    public void closeThread() {
        closed = true;
    }
}
