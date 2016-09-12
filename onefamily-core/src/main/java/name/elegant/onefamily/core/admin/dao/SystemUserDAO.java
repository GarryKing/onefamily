package name.elegant.onefamily.core.admin.dao;

import name.elegant.onefamily.client.dataobject.admin.SystemUser;

/**
 * Author: Garry King
 * Date: 12-12-14 ионГ2:43
 * E-mail:flyhzq@sina.com
 */
public interface SystemUserDAO {

    public Long insertUser(SystemUser user);

    public SystemUser queryUserById(long userId);

    public SystemUser queryUserByName(String userNick);
}
