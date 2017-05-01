package com.metis._metistemplate_.web.view;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public final class JsonUtil {
    
    private static final Logger LOGGER = LogManager.getLogger(JsonUtil.class);
    
    private JsonUtil() {

    }

    public static void toJsonResult(HttpServletResponse response, ObjectMapper mapper, Object result) {
       toJsonResult(response, mapper, result, null);
    }
    
    public static void toJsonResult(HttpServletResponse response, ObjectMapper mapper, Object result,
            SimpleFilterProvider filters) {
        try {
            String json;
            if (filters == null) {
                json = mapper.writeValueAsString(result);
            } else {
                filters.setFailOnUnknownId(false);
                json = mapper.writer(filters).writeValueAsString(result);
            }
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
