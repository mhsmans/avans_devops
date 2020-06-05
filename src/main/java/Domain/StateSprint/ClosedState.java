package Domain.StateSprint;

import Domain.ExportType;

public class ClosedState implements SprintState {

    Sprint sprint;

    public ClosedState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void nextState() {
        System.err.println("sprint is closed");
    }

    @Override
    public void createReport(ExportType exportType) {
        sprint.getReportFactory().createReport(exportType);
    }

    @Override
    public void cancelRelease() {
        System.err.println("sprint is closed");
    }
}
