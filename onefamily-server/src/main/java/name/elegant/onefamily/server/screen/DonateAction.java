package name.elegant.onefamily.server.screen;

import com.alibaba.fastjson.JSON;
import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;
import name.elegant.onefamily.core.admin.service.DonateService;
import name.elegant.onefamily.core.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Garry King
 * Date: 13-2-11 上午1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/")
public class DonateAction extends BaseScreen {

    @Autowired
    private DonateService donateService;

    private static final int PAGE_SIZE = 15;

    @RequestMapping("/donatePage.html")
    public ModelAndView loadPage(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        List<DonateDO> resultList = donateService.queryDonateByPageNo(getPageNo(request), PAGE_SIZE, request.getParameter("keyWord"));
        return new ModelAndView("screen/donatePage", getListPageResult(request, "donatePage", resultList));
    }

    @RequestMapping(value = "/saveDonate.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            String message = donateService.updateDonate(assembleDO(request, response));
            if (StringUtil.isBlank(message)) {
                request.getSession().setAttribute("message", request.getParameter("serialId") + " 的信息已经保存成功！");
            } else {
                request.getSession().setAttribute("message", message);
            }
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("serialId") + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/donatePage.html");
    }

    @RequestMapping(value = "/newDonate.from", produces = {"application/json;charset=GBK"})
    public ModelAndView newFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            donateService.insertDonate(assembleDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("serialId") + " 的信息已经创建成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("serialId") + " 的信息创建失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/donatePage.html");
    }

    private DonateDO assembleDO(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String donateIdStr = request.getParameter("donateId");
        String contributorBizId = request.getParameter("contributorBizId");
        String aidedBizId = request.getParameter("aidedBizId");
        String serialId = request.getParameter("serialId");
        String type = request.getParameter("type");
        String payAmount = request.getParameter("payAmount");
        String payTime = request.getParameter("payTime");
        String accountStatus = request.getParameter("accountStatus");
        String feedbackContent = request.getParameter("feedbackContent");
        String feedbackTime = request.getParameter("feedbackTime");
        String feedbacker = request.getParameter("feedbacker");
        String remark = request.getParameter("remark");

        DonateDO target = new DonateDO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        target.setDonateId(StringUtil.isBlank(donateIdStr) ? 0 : Long.parseLong(donateIdStr));
        target.setContributorBizId(contributorBizId);
        target.setAidedBizId(aidedBizId);
        target.setSerialId(serialId);
        target.setType(type);
        target.setPayAmount(payAmount);
        target.setPayTime(sdf.parse(payTime));
        target.setAccountStatus(accountStatus);
        target.setFeedbackContent(feedbackContent);
        target.setFeedbackTime(sdf.parse(feedbackTime));
        target.setFeedbacker(feedbacker);
        target.setRemark(remark);

        return target;
    }

}
