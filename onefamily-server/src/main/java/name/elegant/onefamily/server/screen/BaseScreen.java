package name.elegant.onefamily.server.screen;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by GarryKing on 2016/8/17.
 * E-mail：flyhzq@sina.com
 */
public class BaseScreen {

    /**
     * 从 session 中判断是否已经登录
     *
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
        return isLogin == null ? false : isLogin;
    }

    /**
     * 从 session 中获取用户昵称
     *
     * @param request
     * @return
     */
    public String getSessionNick(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("nick");
    }

}
