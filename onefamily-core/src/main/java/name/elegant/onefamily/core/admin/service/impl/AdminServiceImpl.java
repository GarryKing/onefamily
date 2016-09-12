package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.admin.SystemUser;
import name.elegant.onefamily.core.admin.dao.SystemUserDAO;
import name.elegant.onefamily.core.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Garry King
 * Date: 12-12-6 ÉÏÎç12:57
 * E-mail:flyhzq@sina.com
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SystemUserDAO systemUserDAO;

    public boolean checkPassword(String userName, String password) {
        SystemUser systemUser = systemUserDAO.queryUserByName(userName);
        return systemUser != null && systemUser.getPassword().equals(password);
    }

}
