package Domain.StateBacklogItem;

import Domain.Activity;

public class ToDoState implements BacklogItemState {

    BacklogItem backlogItem;

    public ToDoState(BacklogItem backlogItem) {
        this.backlogItem = backlogItem;
    }

    @Override
    public void nextState() {
        backlogItem.setBacklogItemState(backlogItem.getDoingState());
    }

    @Override
    public void initializeState() {
        System.err.println("backlog item is already in its initial state");
    }

    @Override
    public void addActivity(Activity activity) {
        if (!backlogItem.getActivities().contains(activity)) {
            backlogItem.getActivities().add(activity);
        } else {
            System.err.println("activity already exists in backlog item");
        }
    }
}
