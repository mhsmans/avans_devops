package Domain;

import Domain.StateBacklogItem.BacklogItem;

import java.util.ArrayList;

public class Forum {

    private ArrayList<DiscussionThread> discussionThreads;

    public Forum() {
        this.discussionThreads = new ArrayList<>();
    }

    public void addDiscussionThread(BacklogItem item, String title) {
        DiscussionThread discussionThread = new DiscussionThread(title, item);

        if (item.isCompleted()) {
            System.err.println("unable to add discussion thread; item is closed");
        } else if (!discussionThreads.contains(discussionThread)) {
            discussionThreads.add(discussionThread);
        } else {
            System.err.println("discussion thread already exists");
        }
    }
}
