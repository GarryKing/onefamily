package name.elegant.onefamily.server.screen;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;
import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.client.dataobject.onefamily.ParticipantDO;
import name.elegant.onefamily.core.admin.service.ActService;
import name.elegant.onefamily.client.dataobject.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author: Garry King
 * Date: 13-2-11 上午1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/")
public class ActAction extends BaseScreen {

    @Autowired
    private ActService actService;

    private static final int PAGE_SIZE = 15;

    @RequestMapping("/actPage.html")
    public ModelAndView loadPage(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        List<ActDO> resultList = actService.queryActByPageNo(getPageNo(request), PAGE_SIZE, request.getParameter("keyWord"));
        return new ModelAndView("screen/actPage", getListPageResult(request, "actPage", resultList));
    }

    @RequestMapping(value = "/deleteParticipant.do", method = RequestMethod.GET, produces = {"application/json;charset=GBK"})
    @ResponseBody
    public Object exist(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return null;
        try {
            actService.deleteParticipant(request.getParameter("actId"), request.getParameter("contributorBizId"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/saveAct.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            actService.updateAct(assembleActDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息已经保存成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/actPage.html");
    }

    @RequestMapping(value = "/saveActParticipant.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveParticipantFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            try {
                Integer.parseInt(request.getParameter("thisActDuration"));
            } catch (Exception e) {
                request.getSession().setAttribute("message", request.getParameter("contributorBizId") + " 的参与时长请输入整数，单位为小时！");
                return new ModelAndView("redirect:/onefamily/actPage.html");
            }
            actService.updateParticipant(assembleParticipantDO(request));
            request.getSession().setAttribute("message", request.getParameter("contributorBizId") + " 的信息已经保存成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("contributorBizId") + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/actPage.html");
    }

    @RequestMapping(value = "/newAct.from", produces = {"application/json;charset=GBK"})
    public ModelAndView newFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            actService.insertAct(assembleActDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息已经创建成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息创建失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/actPage.html");
    }

    private ActDO assembleActDO(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String actIdStr = request.getParameter("actId");
        String actBizId = request.getParameter("actBizId");
        String actName = request.getParameter("actName");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        ActDO target = new ActDO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        target.setActId(StringUtil.isBlank(actIdStr) ? 0 : Long.parseLong(actIdStr));
        target.setActBizId(actBizId);
        target.setActName(actName);
        target.setStartTime((startTime == null || "".equals(startTime)) ? null : sdf.parse(startTime));
        target.setEndTime((endTime == null || "".equals(endTime)) ? null : sdf.parse(endTime));

        return target;
    }

    private ParticipantDO assembleParticipantDO(HttpServletRequest request) {
        ParticipantDO participantDO = new ParticipantDO();
        String actIdStr = request.getParameter("actId");
        String bizId = request.getParameter("contributorBizId");
        String duration = request.getParameter("thisActDuration");
        String level = request.getParameter("thisStarLevel");
        String role = request.getParameter("role");

        participantDO.setActId(StringUtil.isBlank(actIdStr) ? 0 : Long.parseLong(actIdStr));
        participantDO.setContributorBizId(bizId);
        participantDO.setThisStarLevel(level);
        participantDO.setRole(role);
        participantDO.setThisActDuration(Integer.parseInt(duration) + "");
        return participantDO;
    }

}
