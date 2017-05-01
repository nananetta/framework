package com.konkow.framework.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.konkow.framework.service.impl.ConfigService;

@Component
class MessageSenderService implements Runnable {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private ConfigService configService;
    
    private MimeMessagePreparator messagePreparator;

    public MimeMessagePreparator getMessagePreparator() {
        return messagePreparator;
    }

    public void setMessagePreparator(MimeMessagePreparator messagePreparator) {
        this.messagePreparator = messagePreparator;
    }

    public void run() {
        mailSender.send(messagePreparator);
    }

}
