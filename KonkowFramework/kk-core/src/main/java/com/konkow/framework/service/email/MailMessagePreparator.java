package com.konkow.framework.service.email;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.konkow.framework.domain.email.EmailEntity;

@Component
public class MailMessagePreparator implements MimeMessagePreparator {

    private static final Logger LOGGER = LogManager.getLogger(MailMessagePreparator.class);
    
    private static ResourceBundle res;

    private List<String> tos;
    private String filename;
    private EmailEntity entity;
    private String baseDir;

    public MailMessagePreparator(List<String> tos, String filename, EmailEntity entity) {
        this.tos = tos;
        this.filename = filename;
        this.entity = entity;
    }

    public MailMessagePreparator() {

    }

    public void prepare(MimeMessage mimeMessage) throws MessagingException {
        final String[] arrs = new String[tos.size()];
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        Locale locale = new Locale("th");
        res = ResourceBundle.getBundle("com.konkow.framework.emailtemplate." + entity.getTemplateName(), locale);

        String topic = res.getString("topic");
        String content = res.getString("content");

        int index = 0;
        for (Iterator<String> i = entity.getVariables().iterator(); i.hasNext(); index++) {
            String value = i.next();
            content = content.replace("{{" + index + "}}", value);
        }

        message.setTo(tos.toArray(arrs));
        message.setSubject(topic);
        message.setText(content, true);

        LOGGER.info("filename: " + filename);
        if (filename != null) {
            String fullPathName = baseDir + filename;
            final File newFile = new File(fullPathName);
            message.addAttachment(newFile.getName(), newFile);
            LOGGER.info("fullPathName: " + fullPathName);
        }
    }

    public List<String> getTos() {
        return tos;
    }

    public void setTos(List<String> tos) {
        this.tos = tos;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public EmailEntity getEntity() {
        return entity;
    }

    public void setEntity(EmailEntity entity) {
        this.entity = entity;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

}
