package Domain.StateBacklogItem;

import Domain.Activity;

public class DoingState implements BacklogItemState{

    BacklogItem backlogItem;

    public DoingState(BacklogItem backlogItem) {
        this.backlogItem = backlogItem;
    }

    @Override
    public void nextState() {
        if (backlogItem.activitiesCompleted()) {
            backlogItem.setBacklogItemState(backlogItem.getDoneState());
        } else {
            System.err.println("not all activities are completed");
        }
    }

    @Override
    public void initializeState() {
        System.err.println("it is not possible to move backlog item from doing to to-do");
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
