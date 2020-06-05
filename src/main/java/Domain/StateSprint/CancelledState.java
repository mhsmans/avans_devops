package Domain.StateSprint;

import Domain.ExportType;

public class CancelledState implements SprintState {

    Sprint sprint;

    public CancelledState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void nextState() {
        System.err.println("sprint is cancelled");
    }

    @Override
    public void createReport(ExportType exportType) {
        sprint.getReportFactory().createReport(exportType);
    }

    @Override
    public void cancelRelease() {
        System.err.println("sprint is already cancelled");
    }
}
