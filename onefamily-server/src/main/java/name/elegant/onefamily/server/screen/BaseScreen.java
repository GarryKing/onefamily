package name.elegant.onefamily.server.screen;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by GarryKing on 2016/8/17.
 * E-mail��flyhzq@sina.com
 */
public class BaseScreen {

    /**
     * �� session ���ж��Ƿ��Ѿ���¼
     *
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
        return isLogin == null ? false : isLogin;
    }

    /**
     * �� session �л�ȡ�û��ǳ�
     *
     * @param request
     * @return
     */
    public String getSessionNick(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("nick");
    }

}
