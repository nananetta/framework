package com.metis.framework.web.view.report;

public enum ReportType {
    PDF("PDF"), EXCEL("EXCEL"), HTML("HTML"), VIEW("VIEW");

    private String value;

    private ReportType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
