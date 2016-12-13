package name.elegant.onefamily.server.screen;

import name.elegant.onefamily.client.dataobject.util.text.StringUtil;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GarryKing on 2016/8/17.
 * E-mail：flyhzq@sina.com
 */
public class BaseScreen {

    protected static final int PAGE_SIZE = 15;

    /**
     * 从 session 中判断是否已经登录
     *
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request) {
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
        return isLogin == null ? false : isLogin;
    }

    /**
     * 从 session 中获取用户昵称
     *
     * @param request
     * @return
     */
    public String getSessionNick(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("nick");
    }


    public Map<String, Object> getListPageResult(HttpServletRequest request, String navSelector, List resultList) {
        Map<String, Object> result = new HashMap<String, Object>();
        // 提示信息处理
        String message = (String) request.getSession().getAttribute("message");
        if (!StringUtil.isBlank(message)) {
            request.getSession().setAttribute("message", null);
        }

        int pageNo = getPageNo(request);
        boolean isEmpty = CollectionUtils.isEmpty(resultList) || resultList.size() == 0;
        boolean isEnd = isEmpty || resultList.size() < PAGE_SIZE;
        int prev = pageNo <= 1 ? 1 : (pageNo - 1);
        int next = isEmpty ? pageNo : (isEnd ? pageNo : (pageNo + 1));

        if (isEmpty && pageNo != 1) message = "没有更多数据了...";

        result.put("resultList", resultList);
        result.put(navSelector, true);
        result.put("curr", pageNo);
        result.put("prev", prev);
        result.put("next", next);
        result.put("message", message);
        result.put("keyWord", request.getParameter("keyWord"));
        return result;
    }

    public int getPageNo(HttpServletRequest request) {
        String pageNoStr = request.getParameter("pageNo");
        Integer pageNo = StringUtil.isBlank(pageNoStr) ? 1 : Integer.parseInt(pageNoStr);
        if (pageNo == null || pageNo < 1) pageNo = 1;
        return pageNo;
    }

}
