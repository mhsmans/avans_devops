package Domain;

import java.util.Date;

public class ReportFooter {

    private String companyName, projectName, version;
    private Date date;

    public ReportFooter(String companyName, String projectName, String version, Date date) {
        this.companyName = companyName;
        this.projectName = projectName;
        this.version = version;
        this.date = date;
    }
}
