package Domain.StateSprint;

import Domain.ExportType;

public class ReviewState implements SprintState {

    Sprint sprint;

    public ReviewState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void nextState() {
        if (sprint.getSprintReview() != null) {
            sprint.setSprintState(sprint.getClosedState());
        } else {
            System.err.println("sprint review is required for closing sprint");
        }
    }

    @Override
    public void createReport(ExportType exportType) {
        sprint.getReportFactory().createReport(exportType);
    }

    @Override
    public void cancelRelease() {
        System.err.println("unable to cancel non-release sprint");
    }
}
