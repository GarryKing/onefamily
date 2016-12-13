package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.ContributorDO;
import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;
import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;
import name.elegant.onefamily.core.admin.dao.ContributorDAO;
import name.elegant.onefamily.core.admin.dao.DonateDAO;
import name.elegant.onefamily.core.admin.dao.PeerDAO;
import name.elegant.onefamily.core.admin.service.ContributorService;
import name.elegant.onefamily.core.admin.service.PeerService;
import name.elegant.onefamily.client.dataobject.util.text.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by GarryKing on 2016/12/3.
 */
@Service
public class PeerServiceImpl implements PeerService {

    @Autowired
    private PeerDAO peerDAO;

    @Autowired
    private DonateDAO donateDAO;

    @Autowired
    private ContributorDAO contributorDAO;

    public void insertPeer(PeerDO peerDO) {
        ContributorDO contributorDO = contributorDAO.queryContributorByBizId(peerDO.getContributorBizId());
        if (contributorDO != null) peerDO.setContributorId(contributorDO.getContributorId());
        peerDAO.insertPeer(peerDO);
    }

    public void updatePeer(PeerDO peerDO) {
        ContributorDO contributorDO = contributorDAO.queryContributorByBizId(peerDO.getContributorBizId());
        if (contributorDO != null) peerDO.setContributorId(contributorDO.getContributorId());
        peerDAO.updatePeer(peerDO);
    }

    public PeerDO queryPeerById(long peerId) {
        return null;
    }

    public List<PeerDO> queryPeerByPageNo(int pageNo, int size, String keyWord) {
        List<PeerDO> list = peerDAO.queryPeerByPageNo(pageNo, size, keyWord);
        fillList(list);
        return list;
    }

    private void fillList(List<PeerDO> list) {
        if (list != null) {
            for (PeerDO peerDO : list) {
                List<DonateDO> result = donateDAO.queryDonateByPeerId(peerDO.getPeerId());
                double payAmount = 0.00D;
                double lastPayAmount = 0.00D;
                Date lastPayTime = null;
                if (result != null) {
                    for (DonateDO donateDO : result) {
                        try {
                            payAmount += MoneyUtil.stringToMoney(donateDO.getPayAmount()).doubleValue();
                            if (lastPayTime == null || (lastPayTime.getTime() < donateDO.getPayTime().getTime())) {
                                lastPayAmount = MoneyUtil.stringToMoney(donateDO.getPayAmount()).doubleValue();
                                lastPayTime = donateDO.getPayTime();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                peerDO.setLastPayAmount(lastPayAmount);
                peerDO.setLastPayTime(lastPayTime);
                peerDO.setTotalAidedAmount(payAmount);
            }
        }
    }

}
