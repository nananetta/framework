package com.metis._orgname_._metistemplate_.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.google.common.io.Closeables;

public final class ErrorProperties {

    private static final Logger LOGGER = LogManager.getLogger(ErrorProperties.class);
    protected static Properties p = new Properties();

    static {
        ClassPathResource resource = new ClassPathResource("error.properties");
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

    protected ErrorProperties() {

    }

    public static String get(String propertyName) {
        return p.getProperty(propertyName);
    }

}
