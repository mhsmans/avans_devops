package Domain;

import Domain.StateBacklogItem.BacklogItem;

import java.util.ArrayList;

public abstract class Backlog {

    private ArrayList<BacklogItem> backlogItems;

    public Backlog() {
        this.backlogItems = new ArrayList<>();
    }

    final public void addItem(BacklogItem item) {
        if (!backlogItems.contains(item)) {
            backlogItems.add(item);
        } else {
            System.err.println("Backlog item already exists in backlog");
        }
    }

    final public void removeItem(BacklogItem item) {
        if (backlogItems.contains(item)) {
            backlogItems.remove(item);
        } else {
            System.out.println("Backlog item is already removed from backlog");
        }
    }
}
