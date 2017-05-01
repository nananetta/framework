package com.metis._metistemplate_.web.view.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.konkow.framework.domain.Page;
import com.konkow.framework.domain.Result;
import com.konkow.framework.domain.ume.Role;
import com.konkow.framework.domain.ume.RoleQuery;
import com.konkow.framework.domain.ume.User;
import com.konkow.framework.domain.ume.UserQuery;
import com.konkow.framework.repository.IUserRepository;
import com.konkow.framework.service.IAuthorizationService;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    @Qualifier("authorizationService")
    private IAuthorizationService authorizationService;

    @Autowired
    @Qualifier("userRepository")
    private IUserRepository uRepository;

    @RequestMapping(value = "/isAuthorized", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody Boolean isAuthorized(@RequestParam String objectCode,
            @RequestParam(defaultValue = "") String value1, @RequestParam(defaultValue = "") String value2,
            @RequestParam(defaultValue = "") String value3, @RequestParam(defaultValue = "") String value4,
            @RequestParam(defaultValue = "") String value5) {
        if (StringUtils.isEmpty(objectCode)) {
            return false;
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean result = authorizationService.isAuthorizedByUser(objectCode, userDetails.getUsername(), value1, value2,
                value3, value4, value5);
        return result;
    }

    @RequestMapping(value = "/getProfile", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody User getUserProfile() {
        if (SecurityContextHolder.getContext().getAuthentication() == null
                || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            LOGGER.info("cannot get profile. user not logged in");
            return null;
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = uRepository.findByUserCode(userDetails.getUsername());
        return user;
    }

    @RequestMapping(value = "/getAuthorize", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody User getAuthorize(@RequestParam String objectCode,
            @RequestParam(defaultValue = "") String value1, @RequestParam(defaultValue = "") String value2,
            @RequestParam(defaultValue = "") String value3, @RequestParam(defaultValue = "") String value4,
            @RequestParam(defaultValue = "") String value5) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = authorizationService.getAuthorizedByUser(userDetails.getUsername());
        return user;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody User save(@RequestBody User user) {
        LOGGER.info(user.getRoles().size());
        if (user.getPassword() != null && user.getConfirmPassword() != null && user.getNewPassword() != null) {
            if ((!user.getConfirmPassword().equals("")) && (!user.getPassword().equals(""))
                    && (!user.getNewPassword().equals(""))) {
                if (!user.getNewPassword().equals(user.getPassword())) {
                    if (user.getNewPassword().equals(user.getConfirmPassword())) {
                        user.setPassword(user.getNewPassword());
                    }
                }
            }
        }
        User result = null;
		try {
			result = uRepository.store(user);
		} catch (Exception e) {
		    LOGGER.error(e.getMessage(), e);
		}
        return result;

    }
    
    @RequestMapping(value = "/generatePassword", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody String generatePassword(@RequestParam String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @Transactional
    public @ResponseBody User getById(@RequestParam Long key) {
        User result = uRepository.findByKey(key);
        return result;
    }

    @RequestMapping(value = "/role/search", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Page<Role> searchRole(@RequestBody RoleQuery query) {
        Result<Role> result = uRepository.findByRoleQuery(query);
        return result.getPage(query.getPage());

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody Page<User> search(@RequestBody UserQuery query) {
        Result<User> result = uRepository.findByQuery(query);
        return result.getPage(query.getPage());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody void delete(@RequestBody User user) {
        uRepository.remove(user.getId());

    }

}
