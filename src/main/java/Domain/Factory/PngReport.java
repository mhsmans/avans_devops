package Domain.Factory;

import Domain.ReportContent;
import Domain.ReportFooter;
import Domain.ReportHeader;

public class PngReport implements Report {

    private ReportHeader header;
    private ReportContent content;
    private ReportFooter footer;

    @Override
    public void addHeader(ReportHeader header) {
        this.header = header;
    }

    @Override
    public void addContent(ReportContent content) {
        this.content = content;
    }

    @Override
    public void addFooter(ReportFooter footer) {
        this.footer = footer;
    }

    @Override
    public void exportReport() {
        String message = "exporting png report with ";

        if (header != null) {
            message += "'header' ";
        }
        if (content != null) {
            message += "'content' ";
        }
        if (footer != null) {
            message += "'footer'";
        }
        System.err.println(message);
    }
}
