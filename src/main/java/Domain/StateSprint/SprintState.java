package Domain.StateSprint;

import Domain.ExportType;

public interface SprintState {

    void nextState();

    void createReport(ExportType exportType);

    void cancelRelease();
}
