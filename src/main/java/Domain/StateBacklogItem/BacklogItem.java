package Domain.StateBacklogItem;

import Domain.Activity;
import Domain.DiscussionThread;
import Domain.Observer.Member;

import java.util.ArrayList;

public class BacklogItem {

    private String title;
    private String description;
    private Member assignedTo;
    private ArrayList<Activity> activities;
    private boolean completed;

    private BacklogItemState toDoState, doingState, doneState, currentState;

    public BacklogItem(String title, String description) {
        this.title = title;
        this.description = description;
        this.activities = new ArrayList<>();
        completed = false;

        toDoState = new ToDoState(this);
        doingState = new DoingState(this);
        doneState = new DoneState(this);

        currentState = toDoState;
    }

    void setBacklogItemState(BacklogItemState state) {
        currentState = state;
    }

    public void addActivity(Activity activity) {
        currentState.addActivity(activity);
    }

    public void nextState() {
        currentState.nextState();
    }

    public void initializeState() {
        currentState.initializeState();
    }

    public void assignMember(Member member) {
        if (assignedTo == null) {
            assignedTo = member;
        } else {
            System.err.println("backlog item is already assigned to a member");
        }
    }

    public boolean activitiesCompleted() {
        boolean isCompleted = true;

        for (Activity activity : activities) {
            if (!activity.getCompleted()) {
                isCompleted = false;
            }
        }
        return isCompleted;
    }

    public BacklogItemState getToDoState() {
        return toDoState;
    }

    public BacklogItemState getDoingState() {
        return doingState;
    }

    public BacklogItemState getDoneState() {
        return doneState;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public Member getAssignedTo() {
        return assignedTo;
    }

    public BacklogItemState getCurrentState() {
        return currentState;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
