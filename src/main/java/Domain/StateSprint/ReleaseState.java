package Domain.StateSprint;

import Domain.ExportType;
import Domain.Observer.NotificationPublisher;

public class ReleaseState implements SprintState {

    Sprint sprint;

    public ReleaseState(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public void nextState() {
        if (sprint.getPipelineSuccessful()) {
            sprint.setSprintState(sprint.getClosedState());
            NotificationPublisher.getNotificationPublisher().notifyScrumMaster("Sprint '" + sprint.getTitle() + "' is successfully released.");
            NotificationPublisher.getNotificationPublisher().notifyProductOwner("Sprint '" + sprint.getTitle() + "' is successfully released.");
        } else {
            System.out.println("sprint release failed; pipeline failed");
        }
    }

    @Override
    public void createReport(ExportType exportType) {
        sprint.getReportFactory().createReport(exportType);
    }

    @Override
    public void cancelRelease() {
        NotificationPublisher.getNotificationPublisher().notifyScrumMaster("Release sprint '" + sprint.getTitle() + "' is cancelled. " +
                "Pipeline not executed successfully.");
    }
}
