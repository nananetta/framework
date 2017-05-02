package com.metis.framework.web.view.report.jasper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.metis.framework.web.view.report.Report;
import com.metis.framework.web.view.report.ReportParameters;
import com.metis.framework.web.view.report.ReportType;

public class JasperReportWrapper implements Report {

    private static final Logger LOGGER = LogManager.getLogger(JasperReportWrapper.class);

    private HttpServletResponse response;
    private ReportParameters param;

    public JasperReportWrapper(ReportParameters param, HttpServletResponse response) {
        this.param = param;
        this.response = response;
    }

    public void generate() {
        // Set report filename
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = String.format("reports/%s.jasper", param.getReportFilename());
        String reportFolderPath = String.format("reports/");
        InputStream is = classLoader.getResourceAsStream(filePath);

        // Set property of report engine
        // JasperReport report = new JasperReport();

        // Prepare report parameters
        Map parameters = param.getParams();
        parameters.put("report_folder_path", reportFolderPath);
        JREmptyDataSource emptyDatasource = new JREmptyDataSource();
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(is, parameters, emptyDatasource);

            // Invoke report exporter by type
            if (ReportType.PDF == param.getReportType()) {
                LOGGER.info("Exporting to PDF");
                exportToPDF(jasperPrint);

            } else if (ReportType.EXCEL == param.getReportType()) {
                LOGGER.info("Exporting to EXCEL");
                exportToExcel(jasperPrint);

            } else if (ReportType.HTML == param.getReportType()) {
                LOGGER.info("Exporting as HTML");
                exportToHTML(jasperPrint);

            } else {
                LOGGER.info("Exporting as VIEW");
                exportToView(jasperPrint);
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (JRException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    private void exportToPDF(JasperPrint jasperPrint) throws JRException, IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + param.getReportName() + ".pdf");
        response.setContentType("application/pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    private void exportToExcel(JasperPrint jasperPrint) throws JRException, IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + param.getReportName() + ".xls");
        response.setContentType("application/pdf");

        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

    private void exportToHTML(JasperPrint jasperPrint) throws JRException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(baos));
        exporter.exportReport();
        response.getOutputStream().write(baos.toByteArray());
    }

    private void exportToView(JasperPrint jasperPrint) throws JRException, IOException {
        JasperViewer.viewReport(jasperPrint, false);
        JasperPrintManager.printReport(jasperPrint, false);
    }
}
