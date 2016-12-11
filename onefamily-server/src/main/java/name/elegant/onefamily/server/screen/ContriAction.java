package name.elegant.onefamily.server.screen;

import com.alibaba.fastjson.JSON;
import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.core.admin.service.ContributorService;
import name.elegant.onefamily.core.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/contriPage.html")
    public ModelAndView loadPage(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        List<ContributorDO> contributorList = contributorService.queryContributorByPageNo(getPageNo(request), PAGE_SIZE, request.getParameter("keyWord"));
        return new ModelAndView("screen/contriPage", getListPageResult(request, "contriPage", contributorList));
    }

    @RequestMapping(value = "/saveContri.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            contributorService.updateContributor(assembleContributorDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("contributorName") + " 的信息已经保存成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("contributorName") + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/contriPage.html");
    }

    @RequestMapping(value = "/newContri.from", produces = {"application/json;charset=GBK"})
    public ModelAndView newFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            contributorService.insertContributor(assembleContributorDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("contributorName") + " 的信息已经创建成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("contributorName") + " 的信息创建失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/contriPage.html");
    }

    private ContributorDO assembleContributorDO(HttpServletRequest request, HttpServletResponse response) {
        String contributorIdStr = request.getParameter("contributorId");
        long contributorId = StringUtil.isBlank(contributorIdStr) ? 0 : Long.parseLong(contributorIdStr);
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

        ContributorDO contributorDO = new ContributorDO();
        contributorDO.setContributorId(contributorId);
        contributorDO.setContributorName(contributorName);
        contributorDO.setOriginalBizId(originalBizId);
        contributorDO.setType(Integer.parseInt(type));
        contributorDO.setIdentityCard(identityCard);
        contributorDO.setSex(sex);
        contributorDO.setAge(StringUtil.isBlank(age) ? 0 : Integer.parseInt(age));
        contributorDO.setNationality(nationality);
        contributorDO.setStatus(status);
        contributorDO.setLevel(level);
        contributorDO.setRemark(remark);
        contributorDO.setContactMap(JSON.toJSONString(turnBizArrayToMap(contactMap, 1)));
        contributorDO.setExtraMap(JSON.toJSONString(turnBizArrayToMap(extraMap, 2)));

        return contributorDO;
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
