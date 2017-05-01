package com.metis.framework.web.view.report;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.konkow.framework.FrameworkProperties;

@Component
public class ReportParameters {

    private static final Logger LOGGER = LogManager.getLogger(ReportParameters.class);

    private static String endpoint;
    private static String username;
    private static String password;

    private String reportName;
    private String reportFilename;
    private Map<String, String> params;
    private String pValue;
    private ReportType reportType;

    static {
        endpoint = FrameworkProperties.getReportingEndpoint();
        username = FrameworkProperties.getReportingUsername();
        password = FrameworkProperties.getReportingPassword();
    }

    @SuppressWarnings("unused")
    private ReportParameters() {
    }

    public ReportParameters(String reportName, String reportFilename, Map<String, String> params, ReportType reportType) {
        this.reportName = reportName;
        this.reportFilename = reportFilename;
        this.params = params;
        this.reportType = reportType;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportFilename() {
        return reportFilename;
    }

    public void setReportFilename(String reportFilename) {
        this.reportFilename = reportFilename;
    }

    public String getpValue() {
        return pValue;
    }

    public void setpValue(String pValue) {
        this.pValue = pValue;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        ReportParameters.endpoint = endpoint;
    }

    public String getUsername() {
        return username;
    }

    @SuppressWarnings("static-access")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @SuppressWarnings("static-access")
    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String constructReportRequest() {
        String format = "%s%s&rs:Format=%s%s";
        Set<Entry<String, String>> entrySet = params.entrySet();
        StringBuilder sb = new StringBuilder();
        for (Iterator<Entry<String, String>> iterator = entrySet.iterator(); iterator.hasNext();) {
            Entry<String, String> entry = iterator.next();
            sb.append("&");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());

        }
        String endpointString = String.format(format, endpoint, reportFilename, reportType.getValue(), sb.toString());
        LOGGER.info(endpointString);
        return endpointString;
    }

}
