package name.elegant.onefamily.server.screen;

import name.elegant.onefamily.client.dataobject.onefamily.GroupDO;
import name.elegant.onefamily.client.dataobject.onefamily.MemberDO;
import name.elegant.onefamily.client.dataobject.util.text.StringUtil;
import name.elegant.onefamily.core.admin.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
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
            String groupName = request.getParameter("groupName");
            if (StringUtil.isBlank(groupName)) {
                request.getSession().setAttribute("message", "信息未填写！");
                return new ModelAndView("redirect:/onefamily/groupPage.html");
            }
            groupService.insertGroup(assembleDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("groupName") + " 的信息已经创建成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("groupName") + " 的信息创建失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/groupPage.html");
    }

    @RequestMapping(value = "/saveGroup.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            groupService.updateGroup(assembleDO(request, response), null);
            request.getSession().setAttribute("message", request.getParameter("groupName") + " 的信息已经保存成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("groupName") + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/groupPage.html");
    }

    @RequestMapping(value = "/saveMember.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveMemberFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            MemberDO memberDO = assembleMemberDO(request);
            if (groupService.queryMemberByMemberId(memberDO.getMemberId()) == null)
                groupService.addMember(memberDO);
            else
                groupService.updateMember(memberDO);
            request.getSession().setAttribute("message", request.getParameter("contributorBizId") + " 的信息已经保存成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("contributorBizId") + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/groupPage.html");
    }

    @RequestMapping(value = "/deleteMember.do", method = RequestMethod.GET, produces = {"application/json;charset=GBK"})
    @ResponseBody
    public Object exist(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return null;
        try {
            String memberId = request.getParameter("memberId");
            groupService.deleteMember(StringUtil.isBlank(memberId) ? 0 : Long.parseLong(memberId));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private MemberDO assembleMemberDO(HttpServletRequest request) {
        String memberId = request.getParameter("memberId");
        String donateId = request.getParameter("donateId");
        String contributorBizId = request.getParameter("contributorBizId");
        String payAmount = request.getParameter("payAmount");

        MemberDO target = new MemberDO();
        target.setMemberId(StringUtil.isBlank(memberId) ? 0 : Long.parseLong(memberId));
        target.setDonateId(StringUtil.isBlank(donateId) ? 0 : Long.parseLong(donateId));
        target.setContributorBizId(contributorBizId);
        target.setPayAmount(payAmount);

        return target;
    }

    private GroupDO assembleDO(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String donateId = request.getParameter("donateId");
        String groupName = request.getParameter("groupName");
        String donateTime = request.getParameter("donateTime");
        String remark = request.getParameter("remark");

        GroupDO target = new GroupDO();
        target.setDonateId(StringUtil.isBlank(donateId) ? 0 : Long.parseLong(donateId));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        target.setGroupName(groupName);
        target.setDonateTime((donateTime == null || "".equals(donateTime)) ? null : sdf.parse(donateTime));
        target.setRemark(remark);

        return target;
    }

}
