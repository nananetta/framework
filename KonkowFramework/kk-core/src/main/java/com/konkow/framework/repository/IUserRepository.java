package com.konkow.framework.repository;

import com.konkow.framework.domain.Result;
import com.konkow.framework.domain.ume.Role;
import com.konkow.framework.domain.ume.RoleQuery;
import com.konkow.framework.domain.ume.User;
import com.konkow.framework.domain.ume.UserQuery;

public interface IUserRepository extends Repository<User, Long> {

    User findByUserCode(String key);

    Result<Role> findByRoleQuery(RoleQuery query);

    Result<User> findByQuery(UserQuery query);

}
