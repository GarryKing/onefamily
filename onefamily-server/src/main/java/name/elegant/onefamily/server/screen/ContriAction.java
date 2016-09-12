package name.elegant.onefamily.server.screen;

import name.elegant.onefamily.client.dataobject.onefamily.Contributor;
import name.elegant.onefamily.core.admin.service.ContributorService;
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
 * Date: 13-2-11 ÉÏÎç1:36
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
        if (!isLogin(request))
            return new ModelAndView("redirect:/onefamily/index.html");
        Integer pageNo = (Integer) request.getAttribute("pageNo");
        if (pageNo == null || pageNo < 1)
            pageNo = 1;
        Map<String, Object> result = new HashMap<String, Object>();
        List<Contributor> contributorList = contributorService.queryContributorByPageNo(pageNo, PAGE_SIZE);
        int prev = pageNo <= 1 ? 1 : (pageNo + 1);
        int next = CollectionUtils.isEmpty(contributorList) || contributorList.size() < PAGE_SIZE ? pageNo : (pageNo + 1);

        result.put("contriList", contributorList);
        result.put("contriPage", true);
        result.put("curr", pageNo);
        result.put("prev", prev);
        result.put("next", next);
        return new ModelAndView("screen/contriPage", result);
    }

}
