package Domain.StateBacklogItem;

import Domain.Activity;

public interface BacklogItemState {

    void nextState();

    void initializeState();

    void addActivity(Activity activity);
}
