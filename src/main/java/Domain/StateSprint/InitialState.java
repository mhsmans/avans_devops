package Domain.StateSprint;

import Domain.ExportType;

public class InitialState implements SprintState{

    Sprint sprint;

    public InitialState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void nextState() {
        sprint.setSprintState(sprint.getInProgressState());
    }

    @Override
    public void createReport(ExportType exportType) {
        System.err.println("unable to create report in this phase");
    }

    @Override
    public void cancelRelease() {
        System.err.println("unable to cancel sprint in this phase");
    }
}
