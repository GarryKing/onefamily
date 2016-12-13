package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;
import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.admin.dao.DonateDAO;
import name.elegant.onefamily.core.admin.dao.PeerDAO;
import name.elegant.onefamily.core.admin.service.DonateService;
import name.elegant.onefamily.client.dataobject.util.text.MoneyUtil;
import name.elegant.onefamily.client.dataobject.util.text.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by GarryKing on 2016/12/5.
 */
@Service
public class DonateServiceImpl implements DonateService {

    @Autowired
    private DonateDAO donateDAO;

    @Autowired
    private ContributorDAO contributorDAO;

    @Autowired
    private PeerDAO peerDAO;

    public String insertDonate(DonateDO donateDO) {
        String message = checkInput(donateDO);
        if (message != null) return message;
        Long id = donateDAO.insertDonate(donateDO);
        donateDO = donateDAO.queryDonateById(id);
        donateDO.setSerialId(buildSerialId(donateDO, id));
        return null;
    }

    private String buildSerialId(DonateDO donateDO, Long id) {
        String result = "JZ";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        result += sdf.format(new Date()) + "-";
        result += ((1000000 + id) + "").substring(1);
        return result;
    }


    public String updateDonate(DonateDO donateDO) {
        String message = checkInput(donateDO);
        if (message != null) return message;
        donateDAO.updateDonate(donateDO);
        return null;
    }

    public DonateDO queryDonateById(long donateId) {
        return fillDonateDO(donateDAO.queryDonateById(donateId));
    }

    public List<DonateDO> queryDonateByPageNo(int pageNo, int size, String keyWord) {
        List<DonateDO> list = donateDAO.queryDonateByPageNo(pageNo, size, keyWord);
        if (list != null) {
            for (DonateDO donateDO : list) {
                fillDonateDO(donateDO);
            }
        }
        return list;
    }

    public DonateDO fillDonateDO(DonateDO donateDO) {
        ContributorDO contributorDO = contributorDAO.queryContributorByBizId(donateDO.getContributorBizId());
        PeerDO peerDO = peerDAO.queryPeerByBizId(donateDO.getAidedBizId());
        if (contributorDO != null) {
            donateDO.setContributorName(contributorDO.getContributorName());
            donateDO.setContributorCard(contributorDO.getIdentityCard());
        }
        if (peerDO != null) {
            donateDO.setAidedBizId(peerDO.getBizId());
            donateDO.setAidedName(peerDO.getAidedName());
        }
        return donateDO;
    }

    private boolean checkContributor(DonateDO donateDO) {
        if (donateDO == null || StringUtil.isBlank(donateDO.getContributorBizId())) return false;
        ContributorDO contributorDO = contributorDAO.queryContributorByBizId(donateDO.getContributorBizId());
        if (contributorDO == null) return false;
        donateDO.setContributorId(contributorDO.getContributorId());
        return true;
    }

    private boolean checkPeer(DonateDO donateDO) {
        if (donateDO == null || StringUtil.isBlank(donateDO.getAidedBizId())) return false;
        PeerDO peerDO = peerDAO.queryPeerByBizId(donateDO.getAidedBizId());
        if (peerDO == null) return false;
        donateDO.setPeerId(peerDO.getPeerId());
        return true;
    }

    private String checkInput(DonateDO donateDO) {
        if (!checkContributor(donateDO)) {
            return "没有找到 捐助人编号，请检查！";
        }
        if (!checkPeer(donateDO)) {
            return "没有找到 受助人编号，请检查！";
        }
        if (!MoneyUtil.isNumeric(donateDO.getPayAmount())) {
            return "汇款金额 填写的不是数字，请检查";
        }
        return null;
    }

}
