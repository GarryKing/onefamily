package name.elegant.onefamily.server.screen;

import name.elegant.onefamily.client.dataobject.onefamily.ActDO;
import name.elegant.onefamily.client.dataobject.onefamily.ParticipantDO;
import name.elegant.onefamily.core.admin.service.ActService;
import name.elegant.onefamily.core.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/saveAct.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            actService.updateAct(assembleDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息已经保存成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/actPage.html");
    }

    @RequestMapping(value = "/newAct.from", produces = {"application/json;charset=GBK"})
    public ModelAndView newFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            actService.insertAct(assembleDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息已经创建成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("actName") + " 的信息创建失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/actPage.html");
    }

    private ActDO assembleDO(HttpServletRequest request, HttpServletResponse response) {
        String actIdStr = request.getParameter("actId");
        String actBizId = request.getParameter("actBizId");
        String actName = request.getParameter("actName");

        ActDO target = new ActDO();
        target.setActId(StringUtil.isBlank(actIdStr) ? 0 : Long.parseLong(actIdStr));
        target.setActBizId(actBizId);
        target.setActName(actName);
        target.setStartTime(new Date());
        target.setEndTime(new Date());
        target.setParticipantList(assembleParticipantList(request));

        return target;
    }

    private List<ParticipantDO> assembleParticipantList(HttpServletRequest request) {
        List<ParticipantDO> list = new ArrayList<ParticipantDO>();
        String[] bizIdArray = request.getParameterValues("contributorBizId");
        String[] durationArray = request.getParameterValues("thisActDuration");
        String[] levelArray = request.getParameterValues("thisStarLevel");
        String[] roleArray = request.getParameterValues("role");
        if (CollectionUtils.isEmpty(Arrays.asList(bizIdArray)) ||
                CollectionUtils.isEmpty(Arrays.asList(bizIdArray)) ||
                CollectionUtils.isEmpty(Arrays.asList(bizIdArray)) ||
                CollectionUtils.isEmpty(Arrays.asList(bizIdArray)) ||
                CollectionUtils.isEmpty(Arrays.asList(bizIdArray))
                ) {
            throw new RuntimeException("资料未填写完整");
        }
        for (int i = 0; i < bizIdArray.length; i++) {
            ParticipantDO participantDO = new ParticipantDO();
            participantDO.setContributorBizId(bizIdArray[i]);
            participantDO.setThisActDuration(durationArray[i]);
            participantDO.setThisStarLevel(levelArray[i]);
            participantDO.setRole(roleArray[i]);
            list.add(participantDO);
        }
        return list;
    }

}
