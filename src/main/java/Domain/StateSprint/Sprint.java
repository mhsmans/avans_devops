package Domain.StateSprint;

import Domain.ChainOfResponsibility.Pipeline;
import Domain.ExportType;
import Domain.Factory.ReportFactory;
import Domain.SprintBacklog;
import Domain.Team;

import java.util.Date;

public class Sprint {

    private String title;
    private Date startDate;
    private Date endDate;
    private Team team;
    private SprintBacklog sprintBacklog;
    private Boolean isReleaseSprint, pipelineSuccessful;
    private String sprintReview;
    private ReportFactory reportFactory;
    private Pipeline pipeline;

    private SprintState initialState, inProgressState, releaseState, endState,
            reviewState, cancelledState, closedState, currentState;

    public Sprint(String title, Date startDate, Date endDate, Team team, SprintBacklog sprintBacklog, Boolean isReleaseSprint, Pipeline pipeline) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.team = team;
        this.sprintBacklog = sprintBacklog;
        this.isReleaseSprint = isReleaseSprint;
        this.pipeline = pipeline;

        initialState = new InitialState(this);
        inProgressState = new InProgressState(this);
        releaseState = new ReleaseState(this);
        endState = new EndState(this);
        reviewState = new ReviewState(this);
        cancelledState = new CancelledState(this);
        closedState = new ClosedState(this);
        currentState = initialState;

        pipelineSuccessful = false;
    }

    // used to change some properties when sprint is already created
    public void setSprintProperties(String title, Date startDate, Date endDate) {
        if (currentState == initialState) {
            this.title = title;
            this.startDate = startDate;
            this.endDate = endDate;
        } else {
            System.err.println("unable to change sprint properties in this phase");
        }
    }

    public void nextState() {
        currentState.nextState();
    }

    public void cancelRelease() {
        currentState.cancelRelease();
    }

    public void createReport(ExportType exportType) {
        currentState.createReport(exportType);
    }

    public void uploadReview(String sprintReview) {
        if (currentState == reviewState) {
            this.sprintReview = sprintReview;
        } else {
            System.err.println("unable to upload sprint review in this phase");
        }
    }

    public void startPipeline() {
        if (currentState == releaseState) {
            pipelineSuccessful = pipeline.process();
        } else {
            System.err.println("pipeline can only be started from release phase");
        }
    }

    public void setSprintState(SprintState state) {
        currentState = state;
    }

    public SprintState getInProgressState() {
        return inProgressState;
    }

    public SprintState getReleaseState() {
        return releaseState;
    }

    public SprintState getEndState() {
        return endState;
    }

    public SprintState getReviewState() {
        return reviewState;
    }

    public SprintState getCancelledState() {
        return cancelledState;
    }

    public SprintState getClosedState() {
        return closedState;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Boolean getReleaseSprint() {
        return isReleaseSprint;
    }

    public String getTitle() {
        return title;
    }

    public String getSprintReview() {
        return sprintReview;
    }

    public ReportFactory getReportFactory() {
        return reportFactory;
    }

    public Boolean getPipelineSuccessful() {
        return pipelineSuccessful;
    }
}
