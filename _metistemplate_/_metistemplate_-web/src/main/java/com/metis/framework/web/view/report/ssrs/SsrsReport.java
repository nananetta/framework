package com.metis.framework.web.view.report.ssrs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.metis.framework.web.view.report.ReportParameters;
import com.metis.framework.web.view.report.ReportType;

public class SsrsReport {

    private static final Logger LOGGER = LogManager.getLogger(SsrsReport.class);

    private HttpServletResponse response;
    private ReportParameters param;

    public SsrsReport(ReportParameters param, HttpServletResponse response) {
        this.param = param;
        this.response = response;
    }

    public void generate() {

        // Set report type and filename
        if (ReportType.EXCEL == param.getReportType()) {
            response.setHeader("Content-Disposition", "inline;filename=" + param.getReportFilename() + ".xls");
            response.setContentType("application/vnd.ms-excel");
        } else if (ReportType.PDF == param.getReportType()) {
            response.setHeader("Content-Disposition", "inline;filename=" + param.getReportFilename() + ".pdf");
            response.setContentType("application/pdf");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + param.getReportFilename());
        }

        // Prepare information for reporting services
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(param.getUsername(), param.getPassword());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), creds);

        // Execute reporting services
        String endpoint = param.constructReportRequest();
        HttpGet httpget = new HttpGet(endpoint);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        CloseableHttpResponse resp = null;
        LOGGER.info("Executing request " + httpget.getRequestLine());
        try {
            resp = httpClient.execute(httpget);
            InputStream in = resp.getEntity().getContent();
            feedInputToOutput(in, response.getOutputStream());
        } catch (ClientProtocolException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (resp != null) {
                    resp.close();
                }
                httpClient.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        LOGGER.info("Exporting Report");
    }

    void feedInputToOutput(InputStream in, OutputStream out) throws IOException {
        IOUtils.copy(in, out);
    }

}
