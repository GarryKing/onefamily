package name.elegant.onefamily.core.admin.service.impl;

import name.elegant.onefamily.client.dataobject.onefamily.PeerDO;
import name.elegant.onefamily.core.admin.dao.PeerDAO;
import name.elegant.onefamily.core.admin.service.PeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by GarryKing on 2016/12/3.
 */
@Service
public class PeerServiceImpl implements PeerService {

    @Autowired
    private PeerDAO peerDAO;

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
        return peerDAO.queryPeerByPageNo(pageNo, size, keyWord);
    }

}
