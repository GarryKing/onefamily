package name.elegant.onefamily.server.screen;

import com.alibaba.fastjson.JSON;
import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.core.admin.service.ContributorService;
import name.elegant.onefamily.core.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ContriAction extends BaseScreen {

    @Autowired
    private ContributorService contributorService;

    private static final int PAGE_SIZE = 15;

    @RequestMapping("/contriPage.html")
    public ModelAndView loadPage(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
//        String message = (String) request.getSession().getAttribute("message");
//        if (!StringUtil.isBlank(message)) {
//            request.getSession().setAttribute("message", null);
//        }
        Integer pageNo = (Integer) request.getAttribute("pageNo");
        if (pageNo == null || pageNo < 1)
            pageNo = 1;
        Map<String, Object> result = new HashMap<String, Object>();
        List<ContributorDO> contributorList = contributorService.queryContributorByPageNo(pageNo, PAGE_SIZE);
        int prev = pageNo <= 1 ? 1 : (pageNo + 1);
        int next = CollectionUtils.isEmpty(contributorList) || contributorList.size() < PAGE_SIZE ? pageNo : (pageNo + 1);

        result.put("contriList", contributorList);
        result.put("contriPage", true);
        result.put("curr", pageNo);
        result.put("prev", prev);
        result.put("next", next);
        return new ModelAndView("screen/contriPage", result);
    }

    @RequestMapping(value = "/saveContri.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");

        String contributorId = request.getParameter("contributorId");
        String bizId = request.getParameter("bizId");
        String contributorName = request.getParameter("contributorName");
        String originalBizId = request.getParameter("originalBizId");
        String type = request.getParameter("type");
        String identityCard = request.getParameter("identityCard");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String nationality = request.getParameter("nationality");
        String status = request.getParameter("status");
        String level = request.getParameter("level");
        String remark = request.getParameter("remark");
        String[] contactMap = request.getParameterValues("contactMap");
        String[] extraMap = request.getParameterValues("extraMap");

        try {
            ContributorDO contributorDO = new ContributorDO();
            contributorDO.setContributorId(Long.parseLong(contributorId));
            contributorDO.setBizId(bizId);
            contributorDO.setContributorName(contributorName);
            contributorDO.setOriginalBizId(originalBizId);
            contributorDO.setType(type);
            contributorDO.setIdentityCard(identityCard);
            contributorDO.setSex(sex);
            contributorDO.setAge(Integer.parseInt(age));
            contributorDO.setNationality(nationality);
            contributorDO.setStatus(status);
            contributorDO.setLevel(level);
            contributorDO.setRemark(remark);
            contributorDO.setContactMap(JSON.toJSONString(turnBizArrayToMap(contactMap, 1)));
            contributorDO.setExtraMap(JSON.toJSONString(turnBizArrayToMap(extraMap, 2)));
            contributorService.updateContributor(contributorDO);
            request.getSession().setAttribute("message", contributorName + " 的信息已经保存成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", contributorName + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/contriPage.html");
    }

    private Map<String, String> turnBizArrayToMap(String[] bizArray, int bizType) {
        Map<String, String> map = new HashMap<String, String>();
        List<String> keys = bizType == 1 ? ContributorDO.CONTACT_KEY_MAP : ContributorDO.ALL_EXTRA_KEY;
        String[] keysArray = keys.toArray(new String[keys.size()]);
        if (bizArray.length != keysArray.length)
            throw new RuntimeException("参数个数不正确,bizType=" + bizType + ",bizArray=" + JSON.toJSONString(bizArray));
        for (int i = 0; i < bizArray.length; i++) {
            map.put(keysArray[i], bizArray[i]);
        }
        return map;
    }

}
