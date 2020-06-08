package Domain.StateBacklogItem;

import Domain.Activity;

public class ToDoState implements BacklogItemState {

    BacklogItem backlogItem;

    public ToDoState(BacklogItem backlogItem) {
        this.backlogItem = backlogItem;
    }

    @Override
    public void nextState() {
        if (backlogItem.getAssignedTo() != null) {
            backlogItem.setBacklogItemState(backlogItem.getDoingState());
        } else {
            System.err.println("backlog item needs to be assigned to project member first");
        }
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
