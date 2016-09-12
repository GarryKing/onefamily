package name.elegant.onefamily.server.json;

import name.elegant.onefamily.server.screen.BaseScreen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: Garry King
 * Date: 13-2-11 …œŒÁ1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/json")
public class IndexJson extends BaseScreen {

    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Object doLogin(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("isLogin", true);
        return "≤‚ ‘" + request.getSession().getAttribute("isLogin");
    }

}
