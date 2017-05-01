package com.metis._metistemplate_.web.view;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.konkow.framework.service.impl.ConfigService;

@Controller
@RequestMapping(value = "/config")
public class ConfigController {

    private static ResourceBundle res;

    static {
        Locale locale = new Locale("th");
        res = ResourceBundle.getBundle("com.metis._metistemplate_.screenmessage.messages", locale);
    }

    @Autowired
    private ConfigService csv;

    @RequestMapping(value = "/getConfig", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody String getById(@RequestParam String code) {
        String result = csv.getConfigByKey(code);
        return result;
    }

    @RequestMapping(value = "/getDropdown", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody String getDropdown(@RequestParam("k[]") String[] k) {
        return "";
    }

    @RequestMapping(value = "/listMessages", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody Map<String, String> listExceptionMessages() {
        Map<String, String> msgMap = new HashMap<String, String>();
        Set<String> keyset = res.keySet();
        for (Iterator<String> i = keyset.iterator(); i.hasNext();) {
            String key = i.next();
            msgMap.put(key, res.getString(key));
        }
        return msgMap;
    }
}
