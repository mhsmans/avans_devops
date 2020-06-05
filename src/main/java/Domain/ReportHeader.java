package Domain;

import java.util.Date;

public class ReportHeader {

    private String companyName, projectName, version;
    private Date date;

    public ReportHeader(String companyName, String projectName, String version, Date date) {
        this.companyName = companyName;
        this.projectName = projectName;
        this.version = version;
        this.date = date;
    }
}
