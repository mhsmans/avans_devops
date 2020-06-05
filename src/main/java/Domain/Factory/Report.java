package Domain.Factory;

import Domain.ReportContent;
import Domain.ReportFooter;
import Domain.ReportHeader;

public interface Report {

    void addHeader(ReportHeader header);

    void addContent(ReportContent content);

    void addFooter(ReportFooter footer);

    void exportReport();
}
