package Domain.StateSprint;

import Domain.ExportType;
import Domain.Observer.NotificationPublisher;

public class EndState implements SprintState {

    Sprint sprint;

    public EndState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void nextState() {
        if (sprint.getReleaseSprint()) {
            sprint.setSprintState(sprint.getReleaseState());
        } else {
            sprint.setSprintState(sprint.getReviewState());
        }
    }

    @Override
    public void createReport(ExportType exportType) {
        sprint.getReportFactory().createReport(exportType);
    }

    @Override
    public void cancelRelease() {
        if (sprint.getReleaseSprint()) {
            sprint.setSprintState(sprint.getCancelledState());
            NotificationPublisher.getNotificationPublisher().notifyScrumMaster("Release sprint '" + sprint.getTitle() + "' is cancelled.");
            NotificationPublisher.getNotificationPublisher().notifyProductOwner("Release sprint '" + sprint.getTitle() + "' is cancelled.");
        } else {
            System.out.println("unable to cancel non-release sprint");
        }
    }
}
