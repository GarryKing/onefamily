package name.elegant.onefamily.server.screen;

import com.alibaba.fastjson.JSON;
import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;
import name.elegant.onefamily.core.admin.service.PeerService;
import name.elegant.onefamily.core.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class PeerAction extends BaseScreen {

    @Autowired
    private PeerService peerService;

    private static final int PAGE_SIZE = 15;

    @RequestMapping("/peerPage.html")
    public ModelAndView loadPage(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        List<PeerDO> resultList = peerService.queryPeerByPageNo(getPageNo(request), PAGE_SIZE, request.getParameter("keyWord"));
        return new ModelAndView("screen/peerPage", getListPageResult(request, "peerPage", resultList));
    }

    @RequestMapping(value = "/savePeer.from", produces = {"application/json;charset=GBK"})
    public ModelAndView saveFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            peerService.updatePeer(assemblePeerDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("aidedName") + " 的信息已经保存成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("aidedName") + " 的信息保存失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/peerPage.html");
    }

    @RequestMapping(value = "/newPeer.from", produces = {"application/json;charset=GBK"})
    public ModelAndView newFrom(HttpServletRequest request, HttpServletResponse response) {
        if (!isLogin(request)) return new ModelAndView("redirect:/onefamily/index.html");
        try {
            peerService.insertPeer(assemblePeerDO(request, response));
            request.getSession().setAttribute("message", request.getParameter("aidedName") + " 的信息已经创建成功！");
        } catch (Exception e) {
            request.getSession().setAttribute("message", request.getParameter("aidedName") + " 的信息创建失败，请检查输入内容！！！");
        }
        return new ModelAndView("redirect:/onefamily/peerPage.html");
    }

    private PeerDO assemblePeerDO(HttpServletRequest request, HttpServletResponse response) {
        String peerIdStr = request.getParameter("peerId");
        String contributorIdStr = request.getParameter("contributorId");
        String bizId = request.getParameter("bizId");
        String aidedName = request.getParameter("aidedName");
        String identify = request.getParameter("identify");
        String pic = request.getParameter("pic");
        String birthday = request.getParameter("birthday");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String nationality = request.getParameter("nationality");
        String status = request.getParameter("status");
        String aidedType = request.getParameter("aidedType");
        String remark = request.getParameter("remark");
        String[] contactMap = request.getParameterValues("contactMap");
//        String[] extraMap = request.getParameterValues("extraMap");
        String bank = request.getParameter("bank");
        String account = request.getParameter("account");
        String payee = request.getParameter("payee");
        String peerTime = request.getParameter("peerTime");
        String payPeriod = request.getParameter("payPeriod");

        PeerDO target = new PeerDO();
        target.setPeerId(StringUtil.isBlank(peerIdStr) ? 0 : Long.parseLong(peerIdStr));
        if (!StringUtil.isBlank(contributorIdStr)) target.setContributorId(Long.parseLong(contributorIdStr));
        target.setBizId(bizId);
        target.setAidedName(aidedName);
        target.setIdentify(identify);
        target.setPic(pic);
        target.setBirthday(new Date());
        target.setSex(sex);
        target.setAge(StringUtil.isBlank(age) ? 0 : Integer.parseInt(age));
        target.setNationality(nationality);
        target.setStatus(status);
        target.setAidedType(aidedType);
        target.setRemark(remark);
        target.setContactMap(JSON.toJSONString(turnBizArrayToMap(contactMap)));
        target.setBank(bank);
        target.setAccount(account);
        target.setPayee(payee);
        target.setPeerTime(new Date());
        target.setPayPeriod(payPeriod);

        return target;
    }


    private Map<String, String> turnBizArrayToMap(String[] bizArray) {
        Map<String, String> map = new HashMap<String, String>();
        List<String> keys = PeerDO.CONTACT_KEY_MAP;
        String[] keysArray = keys.toArray(new String[keys.size()]);
        if (bizArray.length != keysArray.length)
            throw new RuntimeException("参数个数不正确 " + ",bizArray=" + JSON.toJSONString(bizArray));
        for (int i = 0; i < bizArray.length; i++) {
            map.put(keysArray[i], bizArray[i]);
        }
        return map;
    }

}
