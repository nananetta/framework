package com.metis.framework.web.view;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.konkow.framework.domain.email.EmailEntity;
import com.konkow.framework.domain.email.EmailItem;
import com.konkow.framework.domain.ume.User;
import com.konkow.framework.service.email.EmailService;

@Controller
@RequestMapping(value = "/forgetpassword")
public class ForgetPasswordController {

    private static final Logger LOGGER = LogManager.getLogger(ForgetPasswordController.class);

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody User reset() {
        EmailEntity entity = new EmailEntity();
        entity.setTemplateName("forgetpassword_template");
        entity.getVariables().add("Zuckerberg");
        entity.getVariables().add("accountid_xxxx");
        entity.getVariables().add("P455w0rd");
        Set<EmailItem> items = new HashSet<EmailItem>();
        EmailItem item = new EmailItem();
        item.setRecipient("siriphat.oumtrakul@gmail.com");
        items.add(item);
        entity.setItems(items);
        try {
            emailService.send(entity);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
