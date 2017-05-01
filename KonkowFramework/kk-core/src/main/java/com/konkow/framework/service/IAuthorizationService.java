package com.konkow.framework.service;

import com.konkow.framework.domain.ume.User;

public interface IAuthorizationService {

    void checkAuthorization(Object object, String authCode, String value1, String value2, String value3, String value4,
            String value5);

    boolean isAuthorizedByUser(String objectName, String user, String value1, String value2, String value3,
            String value4, String value5);

    User getAuthorizedByUser(String user);
}
