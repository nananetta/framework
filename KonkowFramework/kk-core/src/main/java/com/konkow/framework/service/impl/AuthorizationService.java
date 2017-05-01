package com.konkow.framework.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.konkow.framework.domain.ume.User;
import com.konkow.framework.exception.UnauthorizedException;
import com.konkow.framework.service.IAuthorizationService;

@Service
public class AuthorizationService implements IAuthorizationService {

    private static final Logger LOGGER = LogManager.getLogger(AuthorizationService.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void checkAuthorization(Object object, String authCode, String value1, String value2, String value3,
            String value4, String value5) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean authorize = isAuthorizedByUser(authCode, userDetails.getUsername(), value1, value2, value3, value4,
                value5);
        if (!authorize) {
            LOGGER.warn(String.format("%s - User %s is unauthorized on object %s", object.getClass().getSimpleName(),
                    userDetails.getUsername(), authCode));
            throw new UnauthorizedException("ERR-006", String.format(
                    "ขออภัย!<br>ผู้ใช้งานระบบ ไม่มีสิทธิ์ทำรายการนี้ (%s)<br>", authCode));
        }
    }

    @Transactional
    public boolean isAuthorizedByUser(String authCode, String user, String value1, String value2, String value3,
            String value4, String value5) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userCode", user));
        criteria.createAlias("roles", "roles");
        criteria.createAlias("roles.auths", "auths");
        criteria.add(Restrictions.eq("auths.authObjectCode", authCode));

        // Field 1 from-to value check
        Criterion cond1a = Restrictions.and(Restrictions.le("auths.authField1ValueFrom", value1),
                Restrictions.ge("auths.authField1ValueTo", value1));
        Criterion cond1b = Restrictions.and(Restrictions.isNull("auths.authField1ValueFrom"),
                Restrictions.isNull("auths.authField1ValueTo"));
        Criterion cond1 = Restrictions.or(cond1a, cond1b);
        criteria.add(cond1);

        // Field 2 from-to value check
        Criterion cond2a = Restrictions.and(Restrictions.le("auths.authField2ValueFrom", value2),
                Restrictions.ge("auths.authField2ValueTo", value2));
        Criterion cond2b = Restrictions.and(Restrictions.isNull("auths.authField2ValueFrom"),
                Restrictions.isNull("auths.authField2ValueTo"));
        Criterion cond2 = Restrictions.or(cond2a, cond2b);
        criteria.add(cond2);

        // Field 3 from-to value check
        Criterion cond3a = Restrictions.and(Restrictions.le("auths.authField3ValueFrom", value3),
                Restrictions.ge("auths.authField3ValueTo", value3));
        Criterion cond3b = Restrictions.and(Restrictions.isNull("auths.authField3ValueFrom"),
                Restrictions.isNull("auths.authField3ValueTo"));
        Criterion cond3 = Restrictions.or(cond3a, cond3b);
        criteria.add(cond3);

        // Field 4 from-to value check
        Criterion cond4a = Restrictions.and(Restrictions.le("auths.authField4ValueFrom", value4),
                Restrictions.ge("auths.authField4ValueTo", value4));
        Criterion cond4b = Restrictions.and(Restrictions.isNull("auths.authField4ValueFrom"),
                Restrictions.isNull("auths.authField4ValueTo"));
        Criterion cond4 = Restrictions.or(cond4a, cond4b);
        criteria.add(cond4);

        // Field 5 from-to value check
        Criterion cond5a = Restrictions.and(Restrictions.le("auths.authField5ValueFrom", value5),
                Restrictions.ge("auths.authField5ValueTo", value5));
        Criterion cond5b = Restrictions.and(Restrictions.isNull("auths.authField5ValueFrom"),
                Restrictions.isNull("auths.authField5ValueTo"));
        Criterion cond5 = Restrictions.or(cond5a, cond5b);
        criteria.add(cond5);

        boolean boolResult = !criteria.list().isEmpty();
        return boolResult;
    }

    @Transactional
    public User getAuthorizedByUser(String user) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("userCode", user));
        List<User> result = criteria.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }
}
