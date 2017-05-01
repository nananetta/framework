package com.konkow.framework.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.konkow.framework.domain.HibernateResult;
import com.konkow.framework.domain.Result;
import com.konkow.framework.domain.ume.Role;
import com.konkow.framework.domain.ume.RoleQuery;
import com.konkow.framework.domain.ume.User;
import com.konkow.framework.domain.ume.UserQuery;
import com.konkow.framework.repository.AbstractRepository;
import com.konkow.framework.repository.IUserRepository;

@Component
public class UserRepository extends AbstractRepository<User, Long> implements IUserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void remove(Long key) {
        User data = findByKey(key);
        data.getRoles().clear();

        getSession().delete(data);
    }

    public Result<User> findAll() {
        // return new Result<AccountReceive>(sessionFactory, User.class);
        return null;
    }

    public User findByKey(Long key) {
        Criteria criteria = createCriteria(User.class);
        criteria.add(Restrictions.eq("id", key));
        List<User> result = criteria.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public User findByUserCode(String key) {
        Criteria criteria = createCriteria(User.class);
        criteria.add(Restrictions.eq("userCode", key));
        List<User> result = criteria.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public User store(User entity) {
        User data = findByKey(entity.getId());
        User result = (User) getSession().merge(entity);
        return result;
    }

    public Result<Role> findByRoleQuery(RoleQuery query) {
        Criteria criteria = createCriteria(Role.class);

        if (query.getRoleCode() != null && !query.getRoleCode().equals("")) {
            if (query.getRoleCode().contains("*") || query.getRoleCode().contains("?")) {
                criteria = criteria.add(Restrictions.like("roleCode", replaceWildcards(query.getRoleCode())));
            } else {
                criteria = criteria.add(Restrictions.eq("roleCode", query.getRoleCode()));
            }

        }
        if (query.getRoleName() != null && !query.getRoleName().equals("")) {
            if (query.getRoleName().contains("*") || query.getRoleName().contains("?")) {
                criteria = criteria.add(Restrictions.like("roleName", replaceWildcards(query.getRoleName())));
            } else {
                criteria = criteria.add(Restrictions.eq("roleName", query.getRoleName()));
            }
        }

        return new HibernateResult<Role>(sessionFactory, criteria);
    }

    public Result<User> findByQuery(UserQuery query) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria = criteria.createAlias("roles", "r", JoinType.LEFT_OUTER_JOIN);
        if (query.getUserCode() != null && !query.getUserCode().equals("")) {
            if (query.getUserCode().contains("*") || query.getUserCode().contains("?")) {
                criteria = criteria.add(Restrictions.like("userCode", replaceWildcards(query.getUserCode())));
            } else {
                criteria = criteria.add(Restrictions.eq("userCode", query.getUserCode()));
            }

        }
        if (query.getUserName() != null && !query.getUserName().equals("")) {
            if (query.getUserName().contains("*") || query.getUserName().contains("?")) {
                criteria = criteria.add(Restrictions.like("userName", replaceWildcards(query.getUserName())));
            } else {
                criteria = criteria.add(Restrictions.eq("userName", query.getUserName()));
            }
        }
        if (query.getRoleCode() != null && !query.getRoleCode().equals("")) {
            if (query.getRoleCode().contains("*") || query.getRoleCode().contains("?")) {
                criteria = criteria.add(Restrictions.like("r.roleCode", replaceWildcards(query.getRoleCode())));
            } else {
                criteria = criteria.add(Restrictions.eq("r.roleCode", query.getRoleCode()));
            }

        }
        if (query.getRoleName() != null && !query.getRoleName().equals("")) {
            if (query.getRoleName().contains("*") || query.getRoleName().contains("?")) {
                criteria = criteria.add(Restrictions.like("r.roleName", replaceWildcards(query.getRoleName())));
            } else {
                criteria = criteria.add(Restrictions.eq("r.roleName", query.getRoleName()));
            }
        }
        criteria = criteria.setProjection(Projections.property("id"));
        Criteria sCriteria = getSession().createCriteria(User.class, "u");

        sCriteria = sCriteria.add(Subqueries.propertyIn("u.id", criteria));

        return new HibernateResult<User>(sessionFactory, sCriteria);
    }

}
