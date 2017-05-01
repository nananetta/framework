package com.konkow.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.google.common.io.Closeables;

public class FrameworkProperties {

    private static final Logger LOGGER = LogManager.getLogger(FrameworkProperties.class);
    protected static Properties p = new Properties();

    static {
        ClassPathResource resource = new ClassPathResource("project.properties");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            p.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            Closeables.closeQuietly(inputStream);
        }
    }

    protected FrameworkProperties() {

    }

    public static String getDefaultPageSize() {
        return p.getProperty("default.page.size");
    }

    public static String getReportingEndpoint() {
        return p.getProperty("report.url");
    }

    public static String getReportingUsername() {
        return p.getProperty("report.username");
    }

    public static String getReportingPassword() {
        return p.getProperty("report.password");
    }

    public static String getVfsStorageBaseDir() {
        return p.getProperty("basedir.path");
    }
}
