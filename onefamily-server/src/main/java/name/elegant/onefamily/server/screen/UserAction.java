package name.elegant.onefamily.server.screen;

import name.elegant.onefamily.core.admin.service.AdminService;
import name.elegant.onefamily.core.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Garry King
 * Date: 13-2-11 上午1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/")
public class UserAction extends BaseScreen {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/index.html")
    public ModelAndView loadIndex(HttpServletRequest request, HttpServletResponse response) {
        String message = (String) request.getSession().getAttribute("message");
        if (!StringUtil.isBlank(message)) {
            request.getSession().setAttribute("message", null);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isLogin", isLogin(request));
        result.put("message", message);
        return new ModelAndView("screen/index", result);
    }

    @RequestMapping(value = "/login.from", produces = {"application/json;charset=GBK"})
    public ModelAndView loginFrom(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        boolean isRight = adminService.checkPassword(userName, password);
        if (isRight) {
            request.getSession().setAttribute("isLogin", true);
            request.getSession().setAttribute("userName", userName);
        } else {
            request.getSession().setAttribute("isLogin", false);
            request.getSession().setAttribute("userName", null);
            request.getSession().setAttribute("message", "用户名或密码不正确，请重试...");
        }
        return new ModelAndView("redirect:/onefamily/index.html");
    }

    @RequestMapping(value = "/logout.do", produces = {"application/json;charset=GBK"})
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("isLogin", false);
        request.getSession().setAttribute("userName", null);
        return new ModelAndView("redirect:/onefamily/index.html");
    }

}
