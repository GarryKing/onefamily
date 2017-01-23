package name.elegant.onefamily.server.screen;

import name.elegant.onefamily.client.dataobject.onefamily.GroupDO;
import name.elegant.onefamily.core.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Author: Garry King
 * Date: 13-2-11 ÉÏÎç1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/")
public class GroupAction extends BaseScreen {

    @Autowired
    private GroupService groupService;

    private static final int PAGE_SIZE = 15;

    @RequestMapping("/groupPage.html")
    public ModelAndView loadPage(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        List<GroupDO> resultList = groupService.queryGroupByPageNo(getPageNo(request), PAGE_SIZE, request.getParameter("keyWord"));
        return new ModelAndView("screen/groupPage", getListPageResult(request, "groupPage", resultList));
    }

}
