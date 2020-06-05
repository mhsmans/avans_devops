package Domain.Factory;

import Domain.ExportType;
import Domain.ReportContent;
import Domain.ReportFooter;
import Domain.ReportHeader;

public class ReportFactory {

    private Report report;

    public void createReport(ExportType exportType) {
        if (exportType == ExportType.PDF) {
            report = new PdfReport();
        } else if (exportType == ExportType.PNG) {
            report = new PngReport();
        }
    }

    public void addHeader(ReportHeader header) {
        report.addHeader(header);
    }

    public void addFooter(ReportFooter footer) {
        report.addFooter(footer);
    }

    public void exportReport() {
        report.exportReport();
    }
}
