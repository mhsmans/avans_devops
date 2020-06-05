package Domain;

public class ReportContent {

    private String teamComposition, burndownChart, effort;

    public ReportContent(String teamComposition, String burndownChart, String effort) {
        this.teamComposition = teamComposition;
        this.burndownChart = burndownChart;
        this.effort = effort;
    }
}
