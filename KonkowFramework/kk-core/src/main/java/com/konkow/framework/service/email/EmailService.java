package com.konkow.framework.service.email;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.konkow.framework.data.vfs.VfsPictureStorage;
import com.konkow.framework.domain.email.EmailEntity;
import com.konkow.framework.domain.email.EmailItem;
import com.konkow.framework.service.impl.ConfigService;
import com.konkow.framework.utils.DateUtils;

@Service
public class EmailService {

    private static final Logger LOGGER = LogManager.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConfigService configService;

    @Autowired
    private VfsPictureStorage pictureStorage;

    @Autowired
    private MessageSenderService messageSenderService;

    @Autowired
    private MailMessagePreparator preparator;

    @Transactional
    public EmailEntity send(EmailEntity entity) throws IllegalStateException, IOException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Date date = new Date();
        String time = new SimpleDateFormat("HH:mm").format(date);

        final List<String> tos = new ArrayList<String>();
        for (EmailItem item : entity.getItems()) {
            LOGGER.debug(item.getRecipient());
            if (item.getRecipient() != null) {
                tos.add(item.getRecipient());
            }
        }

        entity.setSentDate(DateUtils.convertDateToBase(date));
        entity.setSentTime(time);

        // Send email
        preparator.setTos(tos);
        preparator.setEntity(entity);
        messageSenderService.setMessagePreparator(preparator);
        messageSenderService.run();

        // create by...
        if (entity.getId() == null) {
            entity.setCreateBy(userDetails.getUsername());
            entity.setCreateDate(date);

        } else {
            entity.setLastUpdateBy(userDetails.getUsername());
            entity.setLastUpdateDate(date);
        }

        return entity;
    }

//    @RequestMapping(value = "/stored", method = RequestMethod.POST)
//    @Transactional
//    public @ResponseBody SendMailLog save(@RequestParam(value = "infoClient") String infoClientString,
//            @RequestParam(value = "file", required = false) final MultipartFile file) throws IllegalStateException,
//            IOException {
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Date date = new Date();
//        String time = new SimpleDateFormat("HH:mm").format(date);
//        String systemFilename = null;
//        final SendMailLog entity = gson.fromJson(infoClientString, SendMailLog.class);
//
//        if (file != null) {
//            // Remove existing attachment and save the new one.
//            pictureStorage.remove(entity.getMailAttach());
//            String uuid = UUID.randomUUID().toString();
//            systemFilename = String.format("%s_%s", uuid.replace("-", ""), file.getOriginalFilename());
//            entity.setMailAttach(file.getOriginalFilename());
//            entity.setMailAttach2(systemFilename);
//            LOGGER.info("systemFilename: " + systemFilename);
//            pictureStorage.store(systemFilename, file.getBytes());
//        }
//
//        final List<String> tos = new ArrayList<String>();
//        for (SendMailLogItem item : entity.getItems()) {
//            LOGGER.info(item.getReceiveMail());
//            if (item.getReceiveMail() != null) {
//                tos.add(item.getReceiveMail());
//            }
//        }
//
//        // Send email
//        preparator.setTos(tos);
//        preparator.setFilename(systemFilename);
//        preparator.setEntity(entity);
//        messageSenderService.setMessagePreparator(preparator);
//        messageSenderService.run();
//
//        // create by...
//        entity.setMailDate(DateUtils.convertDateToBase(date));
//        entity.setMailTime(time);
//        if (entity.getId() == null) {
//            entity.setCreateBy(userDetails.getUsername());
//            entity.setCreateDate(date);
//
//        } else {
//            entity.setLastUpdateBy(userDetails.getUsername());
//            entity.setLastUpdateDate(date);
//        }
//
//        SendMailLog result = sRepository.store(entity);
//        return result;
//    }

    @Transactional
    public void getAttachment(HttpServletResponse response, @PathVariable String filename) {
        byte[] pic = pictureStorage.load(filename);
        try {
            response.getOutputStream().write(pic);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
