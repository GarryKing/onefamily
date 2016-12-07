package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.DonateDO;
import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;
import name.elegant.onefamily.core.admin.dao.DonateDAO;
import name.elegant.onefamily.core.admin.dao.PeerDAO;
import name.elegant.onefamily.core.admin.service.PeerService;
import name.elegant.onefamily.core.util.text.MoneyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void insertPeer(PeerDO peerDO) {
        peerDAO.insertPeer(peerDO);
    }

    public void updatePeer(PeerDO peerDO) {
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
                if (result != null) {
                    for (DonateDO donateDO : result) {
                        try {
                            payAmount += MoneyUtil.stringToMoney(donateDO.getPayAmount()).doubleValue();
                        } catch (Exception e) {
                        }
                    }
                }
                peerDO.setTotalAidedAmount(payAmount);
            }
        }
    }

}
