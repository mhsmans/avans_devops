package Domain.StateBacklogItem;

import Domain.Activity;
import Domain.Observer.NotificationPublisher;

public class DoneState implements BacklogItemState {

    BacklogItem backlogItem;

    public DoneState(BacklogItem backlogItem) {
        this.backlogItem = backlogItem;
    }

    @Override
    public void nextState() {
        System.err.println("backlog item already is in its final state");
    }

    @Override
    public void initializeState() {
        backlogItem.setBacklogItemState(backlogItem.getToDoState());
        NotificationPublisher.getNotificationPublisher().notifyScrumMaster("Backlog item " + backlogItem.getTitle() + " is moved from 'done' to 'to-do'");
    }

    @Override
    public void addActivity(Activity activity) {
        System.err.println("it is not possible to add activities to completed backlog item");
    }
}
