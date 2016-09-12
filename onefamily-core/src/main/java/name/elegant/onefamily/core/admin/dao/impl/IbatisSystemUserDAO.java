package name.elegant.onefamily.core.admin.dao.impl;

import name.elegant.onefamily.client.dataobject.admin.SystemUser;
import name.elegant.onefamily.core.admin.dao.SystemUserDAO;
import name.elegant.onefamily.core.common.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Author: Garry King
 * Date: 12-12-13 ÉÏÎç12:40
 * E-mail:flyhzq@sina.com
 */
@Repository
public class IbatisSystemUserDAO extends BaseDao implements SystemUserDAO {

    public Long insertUser(SystemUser user) {
        return (Long) this.getSqlMapClientTemplate().insert("SystemUserDAO.insertUser", user);
    }

    public SystemUser queryUserById(long userId) {
        return (SystemUser) this.getSqlMapClientTemplate().queryForObject("SystemUserDAO.queryUserById", userId);
    }

    public SystemUser queryUserByName(String userName) {
        return (SystemUser) this.getSqlMapClientTemplate().queryForObject("SystemUserDAO.queryUserByName", userName);
    }

}
