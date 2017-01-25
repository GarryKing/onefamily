package name.elegant.onefamily.server.screen;

import name.elegant.onefamily.client.dataobject.onefamily.GroupDO;
import name.elegant.onefamily.core.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Author: Garry King
 * Date: 13-2-11 上午1:36
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

    @RequestMapping(value = "/newGroup.from", produces = {"application/json;charset=GBK"})
    public ModelAndView newFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            groupService.insertGroup(assembleDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息已经创建成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息创建失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/groupPage.html");
    }

    private GroupDO assembleDO(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String groupName = request.getParameter("groupName");
        String donateTime = request.getParameter("donateTime");
        String remark = request.getParameter("remark");

        GroupDO target = new GroupDO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        target.setGroupName(groupName);
        target.setDonateTime((donateTime == null || "".equals(donateTime)) ? null : sdf.parse(donateTime));
        target.setRemark(remark);

        return target;
    }

}
