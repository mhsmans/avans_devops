package Domain.StateSprint;

import Domain.ExportType;

import java.util.Date;

public class InProgressState implements SprintState {

    Sprint sprint;

    public InProgressState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void nextState() {
        Date currentDate = new Date();

        if (currentDate.after(sprint.getEndDate())) {
            sprint.setSprintState(sprint.getEndState());
        } else {
            System.err.println("unable to end sprint when there is still time left");
        }
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
